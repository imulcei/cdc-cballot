package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.repositories.AdminRepository;
import afpa.fr.cballot.repositories.PersonRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    private final PersonRepository personRepository;

    public AdminService(AdminRepository adminRepository, PersonRepository personRepository) {
        this.adminRepository = adminRepository;
        this.personRepository = personRepository;
    }
}
