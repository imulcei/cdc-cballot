package afpa.fr.cballot.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import afpa.fr.cballot.entities.Person;

public class PersonDTO {
    private UUID id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("firstname")
    private String firstname;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.lastname = person.getLastname();
        this.firstname = person.getFirstname();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

}
