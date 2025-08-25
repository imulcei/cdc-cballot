package afpa.fr.cballot.dtos;

import afpa.fr.cballot.entities.Student;

public class StudentDTO extends PersonDTO {

    private Integer id_pair;
    private Integer id_session;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        super(student);
        if (student.getPair() != null) {
            this.id_pair = student.getPair().getId();
        }
        if (student.getSession() != null) {
            this.id_session = student.getSession().getId();
        }
    }

    public Integer getId_pair() {
        return id_pair;
    }

    public void setId_pair(Integer id_pair) {
        this.id_pair = id_pair;
    }

    public Integer getId_session() {
        return id_session;
    }

    public void setId_session(Integer id_session) {
        this.id_session = id_session;
    }
}
