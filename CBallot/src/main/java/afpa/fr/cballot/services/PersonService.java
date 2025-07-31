package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.mapper.PersonMapper;
import afpa.fr.cballot.repositories.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper mapper;

    public PersonService(PersonRepository personRepository, PersonMapper mapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    }
}
