package afpa.fr.cballot.entities;

import afpa.fr.cballot.dto.StudentDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends Person {

    public Student() {
    }

    public Student(StudentDTO dto) {
        super(dto);
    }
}
