package afpa.fr.cballot.entities;

import afpa.fr.cballot.dto.PersonDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_person;

    @Column(name = "email")
    private String email;

    public Person() {
    }

    public Person(PersonDTO dto) {
        this.id_person = dto.getId_person();
        this.email = dto.getEmail();
    }

    public Integer getId_person() {
        return id_person;
    }

    public void setId_person(Integer id) {
        this.id_person = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
