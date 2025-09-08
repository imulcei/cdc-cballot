package afpa.fr.cballot.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.TeacherDTO;
import afpa.fr.cballot.entities.Teacher;
import afpa.fr.cballot.mappers.TeacherMapper;
import afpa.fr.cballot.repositories.CourseRepository;
import afpa.fr.cballot.repositories.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    private final TeacherMapper mapper;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository, TeacherMapper mapper,
            PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * GetAllTeachers
     * 
     * @return
     * 
     *         Retourne la liste des formateurs
     */
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacher -> new TeacherDTO(teacher))
                .collect(Collectors.toList());
    }

    /**
     * GetOneTeacher
     * 
     * @param id
     * @return
     * 
     *         Retourne un formateur
     */
    public TeacherDTO getOneTeacher(UUID id) {
        return mapper.converteToDTO(teacherRepository.findById(id).orElse(null));
    }

    public List<TeacherDTO> getTeachersForOneCourse(Integer id) {
        return courseRepository.findAllTeacherById(id)
                .stream()
                .map(teacher -> new TeacherDTO(teacher))
                .collect(Collectors.toList());
    }

    /**
     * CreateTeacher
     * 
     * @param dto
     * @return
     * 
     *         Création d'un formateur
     */
    public TeacherDTO createTeacher(TeacherDTO dto) {
        Teacher teacher = mapper.converteToEntity(dto);
        teacher.setPassword(passwordEncoder.encode(dto.getPassword()));
        teacher = teacherRepository.save(teacher);
        dto.setId(teacher.getId());

        return dto;
    }

    /**
     * UpdateTeacher
     * 
     * @param id
     * @param dto
     * @return
     * 
     *         On véfirie si l'ID correspond à un formateur
     *         et on applique les changements dans l'enregistrement
     */
    public TeacherDTO updateTeacher(UUID id, TeacherDTO dto) {
        Optional<Teacher> original = teacherRepository.findById(id);

        if (original.isEmpty()) {
            throw new EntityNotFoundException("Teacher not found");
        }
        if (!id.equals(dto.getId())) {
            throw new IllegalArgumentException("Id mismatch between path and body");
        }

        Teacher teacher = original.get();
        teacher.setLastname(dto.getLastname());
        teacher.setFirstname(dto.getFirstname());
        teacher.setPassword(dto.getPassword());

        return mapper.converteToDTO(teacherRepository.save(teacher));
    }

    /**
     * RemoveTeacher
     * 
     * @param id
     * @param response
     */
    public void removeTeacher(UUID id, HttpServletResponse response) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
