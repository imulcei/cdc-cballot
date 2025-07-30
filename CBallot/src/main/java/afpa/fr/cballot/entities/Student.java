package afpa.fr.cballot.entities;

import afpa.fr.cballot.dto.StudentDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_student;

    public Student() {
    }

    public Student(StudentDTO dto) {
        this.id_student = dto.getId_student();
    }

    public Integer getId_student() {
        return id_student;
    }

    public void setId_student(Integer id_student) {
        this.id_student = id_student;
    }
}
