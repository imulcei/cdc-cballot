package afpa.fr.cballot.entities;

import afpa.fr.cballot.dtos.StudentDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends Person {

    @ManyToOne
    @JoinColumn(name = "id_pair")
    private Pair pair;

    @ManyToOne
    @JoinColumn(name = "id_session")
    private Session session;

    public Student() {
    }

    public Student(StudentDTO dto) {
        super(dto);
    }

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
