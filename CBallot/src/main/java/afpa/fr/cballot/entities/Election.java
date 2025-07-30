package afpa.fr.cballot.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Election {
    @Id
    private Integer id;

    @Column(name = "start_date")
    private Timestamp start_date;

    @Column(name = "start_date")
    private Timestamp end_date;
}
