package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.repositories.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
