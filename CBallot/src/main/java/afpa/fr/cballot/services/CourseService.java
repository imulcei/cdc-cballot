package afpa.fr.cballot.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.CourseDTO;
import afpa.fr.cballot.entities.Course;
import afpa.fr.cballot.entities.Teacher;
import afpa.fr.cballot.mappers.CourseMapper;
import afpa.fr.cballot.mappers.TeacherMapper;
import afpa.fr.cballot.repositories.CourseRepository;
import afpa.fr.cballot.repositories.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CourseService {

    private final CourseRepository repository;
    private final TeacherRepository teacherRepository;

    private final CourseMapper mapper;
    private final TeacherMapper teacherMapper;

    public CourseService(CourseRepository repository, TeacherRepository teacherRepository, CourseMapper mapper, TeacherMapper teacherMapper) {
        this.repository = repository;
        this.teacherRepository = teacherRepository;
        this.mapper = mapper;
        this.teacherMapper = teacherMapper;
    }

    /**
     * GetAllFormations
     * 
     * @return
     * 
     *         Retourne la liste des formations
     */
    public List<CourseDTO> getAllCourses() {
        return repository.findAll()
                .stream()
                .map(course -> new CourseDTO(course))
                .collect(Collectors.toList());
    }

    /**
     * GetOneFormation
     * 
     * @param id
     * @return
     * 
     *         Retourne une formation
     */
    public CourseDTO getOneCourse(Integer id) {
        return mapper.converteToDTO(repository.findById(id).orElse(null));
    }

    /**
     * CreateFormation
     * 
     * @param dto
     * @return
     */
    public CourseDTO createCourse(CourseDTO dto) {
        Course course = mapper.converteToEntity(dto);
        course = repository.save(course);
        dto.setId(dto.getId());

        return dto;
    }

    /**
     * AddTeacherToCourse
     * @param courseId
     * @param teacherId
     * 
     * Intégration d'un formateur dans une formation
     */
    public void addTeacherToCourse(Integer courseId, UUID teacherId) {
        Course course = repository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

        course.getTeachers().add(teacher);
        repository.save(course);
    }

    /**
     * RemoveTeacherToCourse
     * @param courseId
     * @param teacherId
     * 
     * Retire un formateur d'une formation
     */
    public void removeTeacherToCourse(Integer courseId, UUID teacherId) {
        Course course = repository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

        course.getTeachers().remove(teacher);
        repository.save(course);
    }

    /**
     * UpdateFormation
     * 
     * @param id
     * @param dto
     * @return
     */
    public CourseDTO pudateCourse(Integer id, CourseDTO dto) {
        Optional<Course> original = repository.findById(id);

        if (original.isEmpty()) {
            throw new EntityNotFoundException("Formation not found");
        }
        if (!id.equals(dto.getId())) {
            throw new IllegalArgumentException("Id mismatch between path and body");
        }

        Course course = original.get();
        course.setName(dto.getName());

        return mapper.converteToDTO(repository.save(course));
    }

    /**
     * RemoveFormation
     * 
     * @param id
     * @param response
     */
    public void removeCourse(Integer id, HttpServletResponse response) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
