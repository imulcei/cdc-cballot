package afpa.fr.cballot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.SessionDTO;
import afpa.fr.cballot.dtos.SessionWithAllStudentsDTO;
import afpa.fr.cballot.dtos.SessionWithStudentsDTO;
import afpa.fr.cballot.dtos.StudentDTO;
import afpa.fr.cballot.entities.Course;
import afpa.fr.cballot.entities.Session;
import afpa.fr.cballot.entities.Student;
import afpa.fr.cballot.mappers.SessionMapper;
import afpa.fr.cballot.mappers.StudentMapper;
import afpa.fr.cballot.repositories.CourseRepository;
import afpa.fr.cballot.repositories.SessionRepository;
import afpa.fr.cballot.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    private final SessionMapper mapper;
    private final StudentMapper studentMapper;

    public SessionService(SessionRepository sessionRepository, StudentRepository studentRepository, CourseRepository courseRepository,
            SessionMapper mapper, StudentMapper studentMapper) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.mapper = mapper;
        this.studentMapper = studentMapper;
    }

    /**
     * GetOneSession
     * 
     * @param id
     * @return
     * 
     *         Retourne une session
     */
    public SessionWithStudentsDTO getOneSession(Integer id) {
        Session session = sessionRepository.findById(id).orElse(null);

        List<StudentDTO> students = session.getStudents().stream()
                .map(student -> new StudentDTO(student))
                .toList();

        return new SessionWithStudentsDTO(
                session.getId(),
                session.getName(),
                session.getStart_date(),
                session.getEnd_date(),
                session.getCourse().getId(),
                students);
    }

    /**
     * GetOneSessionWithAllStudents
     *
     * @param id
     * @return
     * 
     *         Retourne une session, sa liste de stagiaire et la liste générale de
     *         stagiaire
     */
    public SessionWithAllStudentsDTO getOneSessionWithAllStudents(Integer id) {
        // récupération de la session
        Session session = sessionRepository.findById(id).orElse(null);

        // Liste des étudiants dans la session
        List<StudentDTO> studentsInSession = session.getStudents().stream()
                .map(student -> new StudentDTO(student))
                .toList();

        // Liste de tous les étudiants
        List<StudentDTO> allStudent = studentRepository.findAll().stream()
                .map(student -> new StudentDTO(student))
                .toList();

        return new SessionWithAllStudentsDTO(
                session.getId(),
                session.getName(),
                session.getStart_date(),
                session.getEnd_date(),
                session.getCourse().getId(),
                studentsInSession,
                allStudent);
    }

    /**
     * CreateSession
     * 
     * @param dto
     * @return
     */
    public SessionWithStudentsDTO createSession(SessionWithStudentsDTO dto) {
        SessionDTO sessionDTO = new SessionDTO(
                dto.name(),
                dto.start_date(),
                dto.end_date(),
                dto.courseId());
                Session session = mapper.converteToEntity(sessionDTO);

                Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
                session.setCourse(course);

        List<UUID> studentIds = new ArrayList<>();

        for (StudentDTO student : dto.students()) {
            Student study = studentMapper.converteToEntity(student);
            studentIds.add(study.getId());
        }

        List<Student> students = studentRepository.findAllById(studentIds);

        session.setStudents(students);

        session = sessionRepository.save(session);

        return getOneSession(session.getId());
    }

    /**
     * UpdateSession
     * 
     * @param id
     * @param dto
     * @return
     */
    public SessionDTO updateSession(Integer id, SessionWithStudentsDTO dto) {
        Optional<Session> original = sessionRepository.findById(id);

        if (original.isEmpty()) {
            throw new EntityNotFoundException("Session not found.");
        }
        if (!id.equals(dto.id())) {
            throw new IllegalArgumentException("Id mismatch between path and body.");
        }

        Session session = original.get();
        session.setName(dto.name());
        session.setStart_date(dto.start_date());
        session.setEnd_date(dto.end_date());
        List<UUID> studentIds = new ArrayList<>();

        for (StudentDTO student : dto.students()) {
            Student study = studentMapper.converteToEntity(student);
            studentIds.add(study.getId());
        }

        List<Student> students = studentRepository.findAllById(studentIds);

        session.setStudents(students);

        return mapper.converteToDTO(sessionRepository.save(session));
    }

    /**
     * AddStudentsToSession
     * 
     * @param sessionId
     * @param studentsIds
     */
    public void addStudentsToSession(Integer sessionId, List<UUID> studentsIds) {
        // Vérifier l'existance de la session
        Session session = sessionRepository.findById(sessionId).orElse(null);

        // Récupérer les stagiaires correspondants
        List<Student> students = studentRepository.findAllById(studentsIds);

        if (students.isEmpty()) {
            throw new EntityNotFoundException("No students found for given IDs");
        }

        // Ratacher les stagiaires à la session
        for (Student student : students) {
            student.setSession(session);
        }

        // Sauvegarder les modifications
        studentRepository.saveAll(students);
    }

    /**
     * RemoveSession
     * 
     * @param id
     * @param response
     */
    public void removeSession(Integer id, HttpServletResponse response) {
        if (sessionRepository.existsById(id)) {
            sessionRepository.deleteById(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * RemoveStudentsFromSession
     * 
     * @param id
     * @param studentsIds
     */
    public void removeStudentsFromSession(Integer id, List<UUID> studentsIds) {
        Session session = sessionRepository.findById(id).orElse(null);

        List<Student> students = studentRepository.findAllById(studentsIds);

        if (students.isEmpty()) {
            throw new EntityNotFoundException("No students found for given IDs");
        }

        session.getStudents().removeAll(students);

        sessionRepository.save(session);
    }
}
