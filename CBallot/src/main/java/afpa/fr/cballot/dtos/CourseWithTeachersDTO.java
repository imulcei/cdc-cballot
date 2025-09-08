package afpa.fr.cballot.dtos;

import java.util.List;

public record CourseWithTeachersDTO(
    Integer id,
    String libelle,
    List<TeacherDTO> teachers
) {
}
