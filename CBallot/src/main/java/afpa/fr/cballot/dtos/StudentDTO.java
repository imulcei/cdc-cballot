package afpa.fr.cballot.dtos;

import afpa.fr.cballot.entities.Student;

public class StudentDTO extends PersonDTO {

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        super(student);
    }
}
