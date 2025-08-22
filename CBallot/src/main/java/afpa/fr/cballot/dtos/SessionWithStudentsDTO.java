package afpa.fr.cballot.dtos;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public record SessionWithStudentsDTO(
                Integer id,
                String name,
                @JsonFormat(pattern = "yyyy-MM-dd")
                Date start_date,
                @JsonFormat(pattern = "yyyy-MM-dd")
                Date end_date,
                Integer id_course,
                List<StudentDTO> students) {
}
