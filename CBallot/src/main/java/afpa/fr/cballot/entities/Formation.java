package afpa.fr.cballot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Formation {
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;
}
