package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.entities.Election;
import afpa.fr.cballot.entities.Session;
import afpa.fr.cballot.repositories.ElectionRepository;
import afpa.fr.cballot.repositories.SessionRepository;
import afpa.fr.cballot.services.mail.EmailService;

@Service
public class ElectionService {
    private ElectionRepository electionRepository;
    private EmailService emailService;
    private SessionRepository sessionRepository;

    public ElectionService(ElectionRepository electionRepository, EmailService emailService, SessionRepository sessionRepository) {
        this.electionRepository = electionRepository;
        this.emailService = emailService;
        this.sessionRepository = sessionRepository;
    }

    public Election save(Election election) {
        return electionRepository.save(election);
    }

    public void delete(Election election) {
        electionRepository.delete(election);
    }

    public Session findById(Integer id_session) {
        return sessionRepository.findById(id_session).orElse(null);
    }

    public Election findById_Election(Integer id_election) {
        return electionRepository.findById(id_election).orElse(null);
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

    public void closeElection(Integer id_election) {
        Election election = electionRepository.findById(id_election)
                .orElseThrow(() -> new RuntimeException("Election not found"));
        emailService.sendResultEmails(election);
    }
}
