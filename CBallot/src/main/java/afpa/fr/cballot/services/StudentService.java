package afpa.fr.cballot.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.StudentDTO;
import afpa.fr.cballot.entities.Student;
import afpa.fr.cballot.mappers.StudentMapper;
import afpa.fr.cballot.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper;

    public StudentService(StudentRepository studentRepository, StudentMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    /**
     * GetAllStudents
     * @return
     * 
     * Retourne la liste de stagiaire
     */
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                                .stream()
                                .map(student -> new StudentDTO(student))
                                .collect(Collectors.toList());
    }

    /**
     * GetOneStudent
     * @param id
     * @return
     * 
     * Retourne un stagiaire
     */
    public StudentDTO getOneStudent(UUID id) {
        return mapper.converteToDTO(studentRepository.findById(id).orElse(null));
    }

    /**
     * GetStudentsBySessionId
     *
     * @param id
     * @return
     * 
     * retourne une liste de stagiaire selon l'Id de la session
     */
    public List<StudentDTO> getStudentsBySessionId(Integer id) {
        return studentRepository.findAllBySessionId(id)
                                .stream()
                                .map(student -> new StudentDTO(student))
                                .collect(Collectors.toList());
    }

    /**
     * CreateStudent
     * @param dto
     * @return
     * 
     * Créé et retourne un stagiaire
     */
    public StudentDTO createStudent(StudentDTO dto) {
        return mapper.converteToDTO(studentRepository.save(mapper.converteToEntity(dto)));
    }

    /**
     * UpdateStudent
     * @param id
     * @param dto
     * @return
     * 
     * Vérification des données reçu avant sauvegarde
     * à partir de l'id entrée
     */
    public StudentDTO updateStudent(UUID id, StudentDTO dto) {
        Optional<Student> originalStudent = studentRepository.findById(id);

        if (originalStudent.isEmpty()) {
            throw new EntityNotFoundException("Student not found");
        }

        if (!id.equals(dto.getId())) {
            throw new IllegalArgumentException("Id mismatch between path and body");
        }

        Student student = originalStudent.get();
        student.setLastName(dto.getLastName());
        student.setFirstName(dto.getFirstName());

        return mapper.converteToDTO(studentRepository.save(student));
    }

    /**
     * RemoveStudent
     * @param id
     * @param response
     * 
     * Suppression après recherche de l'entité dans la BDD
     */
    public void removeStudent(UUID id, HttpServletResponse response) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
