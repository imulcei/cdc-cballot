package afpa.fr.cballot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Session {
    @Id
    private Integer id;

    @Column(name = "email")
    private String email;
}
