package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.entities.Election;
import afpa.fr.cballot.repositories.ElectionRepository;
import afpa.fr.cballot.services.mail.EmailService;

@Service
public class ElectionService {
    private ElectionRepository electionRepository;
    private EmailService emailService;

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

    public Election createElection(Election election) {
        Election electionSaved = electionRepository.save(election);
        emailService.sendVoteInvitationEmails(electionSaved);
        return electionSaved;
    }

    public void closeElection(Integer electionId) {
        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new RuntimeException("Election not found"));
        emailService.sendResultEmails(election);
    }
}
