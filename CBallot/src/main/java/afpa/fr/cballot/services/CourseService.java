package afpa.fr.cballot.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.CourseDTO;
import afpa.fr.cballot.entities.Course;
import afpa.fr.cballot.mappers.CourseMapper;
import afpa.fr.cballot.repositories.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CourseService {

    private final CourseRepository repository;

    private final CourseMapper mapper;

    public CourseService(CourseRepository repository, CourseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * GetAllFormations
     * @return
     * 
     * Retourne la liste des formations
     */
    public List<CourseDTO> getAllFormations() {
        return repository.findAll()
                                    .stream()
                                    .map(course -> new CourseDTO(course))
                                    .collect(Collectors.toList());
    }

    /**
     * GetOneFormation
     * @param id
     * @return
     * 
     * Retourne une formation
     */
    public CourseDTO getOneFormation(Integer id) {
        return mapper.converteToDTO(repository.findById(id).orElse(null));
    }

    /**
     * CreateFormation
     * @param dto
     * @return
     */
    public CourseDTO createFormation(CourseDTO dto) {
        Course course = mapper.converteToEntity(dto);
        course = repository.save(course);
        dto.setId(dto.getId());

        return dto;
    }

    /**
     * UpdateFormation
     * @param id
     * @param dto
     * @return
     */
    public CourseDTO updateFormation(Integer id, CourseDTO dto) {
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
     * @param id
     * @param response
     */
    public void removeFormation(Integer id, HttpServletResponse response) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
