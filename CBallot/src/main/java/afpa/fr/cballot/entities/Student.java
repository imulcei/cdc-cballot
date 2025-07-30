package afpa.fr.cballot.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;

@Entity
public class Student {
    @Column(name = "id_unique")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
