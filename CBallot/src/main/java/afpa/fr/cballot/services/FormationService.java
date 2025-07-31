package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.mapper.FormationMapper;
import afpa.fr.cballot.repositories.FormationRepository;

@Service
public class FormationService {

    private final FormationRepository formationRepository;

    private final FormationMapper mapper;

    public FormationService(FormationRepository formationRepository, FormationMapper mapper) {
        this.formationRepository = formationRepository;
        this.mapper = mapper;
    }
}
