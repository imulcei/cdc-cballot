package afpa.fr.cballot.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dto.StudentDTO;
import afpa.fr.cballot.entities.Student;
import afpa.fr.cballot.mapper.StudentMapper;
import afpa.fr.cballot.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;

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
    public StudentDTO getOneStudent(Integer id) {
        return mapper.converteToDTO(studentRepository.findById(id).orElse(null));
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

    public StudentDTO updateStudent(Integer id, StudentDTO dto) {
        Optional<Student> originalStudent = studentRepository.findById(id);

        if (originalStudent.isEmpty()) {
            throw new EntityNotFoundException("Student not found");
        }

        if (!id.equals(dto.getId_student())) {
            throw new IllegalArgumentException("Id mismatch between path and body");
        }

        Student student = originalStudent.get();
        student.set
    }
}
