package afpa.fr.cballot.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.SessionDTO;
import afpa.fr.cballot.dtos.SessionWithAllStudentsDTO;
import afpa.fr.cballot.dtos.StudentDTO;
import afpa.fr.cballot.entities.Session;
import afpa.fr.cballot.entities.Student;
import afpa.fr.cballot.mappers.SessionMapper;
import afpa.fr.cballot.mappers.StudentMapper;
import afpa.fr.cballot.repositories.SessionRepository;
import afpa.fr.cballot.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;

    private final SessionMapper mapper;

    public SessionService(SessionRepository sessionRepository, StudentRepository studentRepository, SessionMapper mapper) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    /**
     * GetAllSessions
     * @return
     * 
     * Retourne une liste de sessions
     */
    public List<SessionDTO> getAllSessions() {
        return sessionRepository.findAll()
                                .stream()
                                .map(session -> new SessionDTO(session))
                                .collect(Collectors.toList());
    }

    /**
     * GetSessionsToThisCourse
     * @param id
     * @return
     * 
     * Retourne une listede session lié à la formation (par l'id)
     */
    public List<SessionDTO> getSessionsToThisCourse(Integer id) {
        return sessionRepository.findByCourseId(id)
                                .stream()
                                .map(session -> new SessionDTO(session))
                                .collect(Collectors.toList());
    }

    /**
     * GetOneSession
     * @param id
     * @return
     * 
     * Retourne une session
     */
    public SessionDTO getOneSession(Integer id) {
        return mapper.converteToDTO(sessionRepository.findById(id).orElse(null));
    }

    /**
     * GetOneSessionWithAllStudents
     *
     * @param id
     * @return
     * 
     * Retourne une session, sa liste de stagiaire et la liste générale de stagiaire
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
            studentsInSession,
            allStudent
        );
    }

    /**
     * CreateSession
     * @param dto
     * @return
     */
    public SessionDTO createSession(SessionDTO dto) {
        Session session = mapper.converteToEntity(dto);
        session = sessionRepository.save(session);
        dto.setId(session.getId());

        return dto;
    }

    /**
     * UpdateSession
     * @param id
     * @param dto
     * @return
     */
    public SessionDTO updateSession(Integer id, SessionDTO dto) {
        Optional<Session> original = sessionRepository.findById(id);

        if (original.isEmpty()){
            throw new EntityNotFoundException("Session not found.");
        }
        if (!id.equals(dto.getId())) {
            throw new IllegalArgumentException("Id mismatch between path and body.");
        }

        Session session = original.get();
        session.setName(dto.getName());
        session.setStart_date(dto.getStart_date());
        session.setEnd_date(dto.getEnd_date());

        return mapper.converteToDTO(sessionRepository.save(session));
    }

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
