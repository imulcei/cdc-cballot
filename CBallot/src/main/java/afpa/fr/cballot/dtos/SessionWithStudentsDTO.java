package afpa.fr.cballot.dtos;

import java.util.Date;
import java.util.List;

public record SessionWithStudentsDTO(
                Integer id,
                String name,
                Date start_date,
                Date end_date,
                Integer id_course,
                List<StudentDTO> students) {
}
