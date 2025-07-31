package afpa.fr.cballot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Student {

    @ManyToOne
    @JoinColumn(name = "id_pair")
    private Pair pair;

    @ManyToOne
    @JoinColumn(name = "id_session")
    private Session session;
}
