package afpa.fr.cballot.dtos;

import java.util.List;

public record CourseWithTeachersDTO(
    Integer id,
    String name,
    List<TeacherDTO> teachers
) {
}
