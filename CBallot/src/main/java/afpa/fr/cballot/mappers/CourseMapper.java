package afpa.fr.cballot.mappers;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.CourseDTO;
import afpa.fr.cballot.entities.Course;

@Service
public class CourseMapper {
    public CourseDTO converteToDTO(Course course) {
        return new CourseDTO(course);
    }

    public Course converteToEntity(CourseDTO dto) {
        return new Course(dto);
    }
}
