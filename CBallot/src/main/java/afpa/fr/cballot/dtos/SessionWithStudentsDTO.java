package afpa.fr.cballot.dtos;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


public record SessionWithStudentsDTO(
        Integer id,
        @JsonProperty("name")
        String name,
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date start_date,
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date end_date,
        @JsonProperty("courseId")
        Integer courseId,
        List<StudentDTO> students) {
}
