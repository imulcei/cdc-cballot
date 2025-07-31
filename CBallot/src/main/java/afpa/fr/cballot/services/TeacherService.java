package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.mapper.TeacherMapper;
import afpa.fr.cballot.repositories.PersonRepository;
import afpa.fr.cballot.repositories.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final PersonRepository personRepository;

    private final TeacherMapper mapper;

    public TeacherService(TeacherRepository teacherRepository, PersonRepository personRepository, TeacherMapper mapper) {
        this.teacherRepository = teacherRepository;
        this.personRepository = personRepository;
        this.mapper = mapper;
    }
}
