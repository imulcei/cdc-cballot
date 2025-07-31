package afpa.fr.cballot.mapper;

import afpa.fr.cballot.dto.CourseDTO;
import afpa.fr.cballot.entities.Course;

public class CourseMapper {
    public CourseDTO converteToDTO(Course course) {
        return new CourseDTO(course);
    }

    public Course converteToEntity(CourseDTO dto) {
        return new Course(dto);
    }
}
