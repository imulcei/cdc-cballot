package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.entities.Election;
import afpa.fr.cballot.repositories.ElectionRepository;

@Service
public class ElectionService {
    private ElectionRepository electionRepository;

    public ElectionService(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    public Election save(Election election) {
        return electionRepository.save(election);
    }

    public void delete(Election election) {
        electionRepository.delete(election);
    }

    public boolean deleteById(Integer id) {
        return electionRepository.findById(id).map(pair -> {
            electionRepository.delete(pair);
            return true;
        }).orElse(false);
    }
}
