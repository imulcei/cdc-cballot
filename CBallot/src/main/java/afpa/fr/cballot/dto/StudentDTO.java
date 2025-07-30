package afpa.fr.cballot.dto;

import afpa.fr.cballot.entities.Student;

public class StudentDTO extends PersonDTO {
    private Integer id_student;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        this.id_student = student.getId_student();
    }

    public Integer getId_student() {
        return id_student;
    }

    public void setId_student(Integer id) {
        this.id_student = id;
    }
}
