package afpa.fr.cballot.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.CourseDTO;
import afpa.fr.cballot.dtos.CourseWithTeachersDTO;
import afpa.fr.cballot.dtos.TeacherDTO;
import afpa.fr.cballot.entities.Course;
import afpa.fr.cballot.entities.Teacher;
import afpa.fr.cballot.mappers.CourseMapper;
import afpa.fr.cballot.repositories.CourseRepository;
import afpa.fr.cballot.repositories.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    private final CourseMapper mapper;

    public CourseService(CourseRepository repository, TeacherRepository teacherRepository, CourseMapper mapper) {
        this.courseRepository = repository;
        this.teacherRepository = teacherRepository;
        this.mapper = mapper;
    }

    /**
     * GetAllFormations
     * 
     * @return
     * 
     *         Retourne la liste des formations
     */
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
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
     *         Retourne une formation et sa liste de formateurs
     */
    public CourseWithTeachersDTO getOneCourse(Integer id) {
        Course course = courseRepository.findById(id).orElse(null);

        List<TeacherDTO> teachers = course.getTeachers().stream()
                .map(teacher -> new TeacherDTO(teacher))
                .toList();

        return new CourseWithTeachersDTO(
                course.getId(),
                course.getLibelle(),
                teachers);
    }

    /**
     * CreateFormation
     * 
     * @param dto
     * @return
     */
    public CourseDTO createCourse(CourseDTO dto) {
        Course course = mapper.converteToEntity(dto);
        course = courseRepository.save(course);
        dto.setId(course.getId());

        return dto;
    }

    /**
     * AddTeacherToCourse
     * 
     * @param courseId
     * @param teacherId
     * 
     *                  Intégration d'un formateur dans une formation
     */
    public CourseWithTeachersDTO addTeacherToCourse(Integer courseId, List<UUID> teacherIds) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        List<Teacher> teachers = teacherRepository.findAllById(teacherIds);

        if (teachers.isEmpty()) {
            throw new EntityNotFoundException("No teachers found for given IDs");
        }

        course.getTeachers().addAll(teachers);
        courseRepository.save(course);

        return getOneCourse(courseId);
    }

    /**
     * RemoveTeacherToCourse
     * 
     * @param courseId
     * @param teacherIds
     * 
     *                   Retire un formateur d'une formation
     */
    public CourseWithTeachersDTO removeTeacherFromCourse(Integer courseId, List<UUID> teacherIds) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        List<Teacher> teachers = teacherRepository.findAllById(teacherIds);

        if (teachers.isEmpty()) {
            throw new EntityNotFoundException("No Teachers found for given IDs");
        }
        course.getTeachers().removeAll(teachers);
        courseRepository.save(course);

        return getOneCourse(courseId);
    }

    /**
     * UpdateFormation
     * 
     * @param id
     * @param dto
     * @return
     */
    public CourseDTO updateCourse(Integer id, CourseDTO dto) {
        Optional<Course> original = courseRepository.findById(id);

        if (original.isEmpty()) {
            throw new EntityNotFoundException("Formation not found");
        }
        if (!id.equals(dto.getId())) {
            throw new IllegalArgumentException("Id mismatch between path and body");
        }

        Course course = original.get();
        course.setName(dto.getLibelle());

        return mapper.converteToDTO(courseRepository.save(course));
    }

    /**
     * RemoveFormation
     * 
     * @param id
     * @param response
     */
    public void removeCourse(Integer id, HttpServletResponse response) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
