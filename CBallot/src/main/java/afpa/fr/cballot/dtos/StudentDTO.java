package afpa.fr.cballot.dtos;

import afpa.fr.cballot.entities.Student;

public class StudentDTO extends PersonDTO {

    private PairDto id_pair;
    private SessionDTO id_session;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        super(student);
    }

    public PairDto getId_pair() {
        return id_pair;
    }

    public void setId_pair(PairDto id_pair) {
        this.id_pair = id_pair;
    }

    public SessionDTO getId_session() {
        return id_session;
    }

    public void setId_session(SessionDTO id_session) {
        this.id_session = id_session;
    }

}
