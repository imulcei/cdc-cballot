package afpa.fr.cballot.dto;

import afpa.fr.cballot.entities.Person;

public class PersonDTO {
    private Integer id_person;
    private String email;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.id_person = person.getId_person();
        this.email = person.getEmail();
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
