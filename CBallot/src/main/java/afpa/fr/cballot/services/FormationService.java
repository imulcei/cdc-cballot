package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.repositories.FormationRepository;

@Service
public class FormationService {

    private final FormationRepository formationRepository;

    public FormationService(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }
}
