package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.repositories.PersonRepository;
import afpa.fr.cballot.repositories.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final PersonRepository personRepository;

    public TeacherService(TeacherRepository teacherRepository, PersonRepository personRepository) {
        this.teacherRepository = teacherRepository;
        this.personRepository = personRepository;
    }
}
