package afpa.fr.cballot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.entities.Election;
import afpa.fr.cballot.entities.Session;
import afpa.fr.cballot.entities.Voter;
import afpa.fr.cballot.repositories.ElectionRepository;
import afpa.fr.cballot.repositories.SessionRepository;
import afpa.fr.cballot.services.mail.EmailService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ElectionService {
    private ElectionRepository electionRepository;
    private EmailService emailService;
    private SessionRepository sessionRepository;
    private VoterService voterService;

    public ElectionService(ElectionRepository electionRepository, EmailService emailService,
            SessionRepository sessionRepository, VoterService voterService) {
        this.electionRepository = electionRepository;
        this.emailService = emailService;
        this.sessionRepository = sessionRepository;
        this.voterService = voterService;
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
        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Election not found."));
        List<Voter> voters = election.getVoters();
        for (Voter voter : voters) {
            voterService.remove(voter);
        }
        electionRepository.delete(election);
        return true;
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
