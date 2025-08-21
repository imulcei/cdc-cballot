package afpa.fr.cballot.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import afpa.fr.cballot.entities.Person;

public class PersonDTO {
    private UUID id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("firstname")
    private String firstName;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.lastName = person.getLastName();
        this.firstName = person.getFirstName();
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
