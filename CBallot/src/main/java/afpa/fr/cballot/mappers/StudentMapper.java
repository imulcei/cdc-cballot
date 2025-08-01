package afpa.fr.cballot.mappers;

import afpa.fr.cballot.dtos.StudentDTO;
import afpa.fr.cballot.entities.Student;

public class StudentMapper {
    public StudentDTO converteToDTO (Student student) {
        return new StudentDTO(student);
    }

    public Student converteToEntity(StudentDTO dto) {
        return new Student(dto);
    }
}
