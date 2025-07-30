package afpa.fr.cballot.mapper;

import afpa.fr.cballot.dto.StudentDTO;
import afpa.fr.cballot.entities.Student;

public class StudentMapper {
    public StudentDTO converteToDTO (Student student) {
        return new StudentDTO(student);
    }

    public Student converteToEntity(StudentDTO dto) {
        return new Student(dto);
    }
}
