package afpa.fr.cballot.mappers;

import afpa.fr.cballot.dtos.PersonDTO;
import afpa.fr.cballot.entities.Person;

public class PersonMapper {
    public PersonDTO converteToDTO(Person person) {
        return new PersonDTO(person);
    }

    public Person converteToEntity(PersonDTO dto) {
        return new Person(dto);
    }
}
