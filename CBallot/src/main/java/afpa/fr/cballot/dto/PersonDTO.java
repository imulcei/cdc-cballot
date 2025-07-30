package afpa.fr.cballot.dto;

import afpa.fr.cballot.entities.Person;

public class PersonDTO {
    private Integer id;
    private String email;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
