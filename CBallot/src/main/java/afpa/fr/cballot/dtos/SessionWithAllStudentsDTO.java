package afpa.fr.cballot.dtos;

import java.util.Date;
import java.util.List;

public record SessionWithAllStudentsDTO(
    Integer id,
    String name,
    Date start_date,
    Date end_date,
    List<StudentDTO> studentsInSession,
    List<StudentDTO> allStudents
) {}
