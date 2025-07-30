package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.repositories.PersonRepository;
import afpa.fr.cballot.repositories.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PersonRepository personRepository;

    public StudentService(StudentRepository studentRepository, PersonRepository personRepository) {
        this.studentRepository = studentRepository;
        this.personRepository = personRepository;
    }
}
