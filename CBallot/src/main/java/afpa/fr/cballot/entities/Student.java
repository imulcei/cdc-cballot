package afpa.fr.cballot.entities;

import afpa.fr.cballot.dto.StudentDTO;
import jakarta.persistence.Entity;

@Entity
public class Student extends Person {

    public Student(StudentDTO dto) {
    }

}
