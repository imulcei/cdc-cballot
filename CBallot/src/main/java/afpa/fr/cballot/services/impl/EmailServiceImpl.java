package afpa.fr.cballot.services.impl;

import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import afpa.fr.cballot.entities.Election;
import afpa.fr.cballot.entities.Pair;
import afpa.fr.cballot.entities.Voter;
import afpa.fr.cballot.services.mail.EmailService;

@Component
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private EmailServiceImpl emailServiceImpl;
    @Autowired
    private SimpleMailMessage mailTemplate;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("noreply@afpa-test-cda.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }

    /**
     * Envoyer un mail après la création d'une élection.
     */
    @Override
    public void sendVoteInvitationEmails(Election election) {
        for (Voter voter : election.getVoters()) {
            String text = String.format(mailTemplate.getText(), generateVoteLink(voter));
            emailServiceImpl.sendSimpleMessage(voter.getEmail(),
                    "Lien pour voter à l’élection " + election.getId(),
                    text);
        }
    }

    private String generateVoteLink(Voter voter) {
        return "http://localhost:8080/vote/" + voter.getId();
    }

    /**
     * Envoyer un mail après la cloture du vote
     */
    @Override
    public void sendResultEmails(Election election) {
        // Récupérer le binôme gagnant
        Pair winner = election.getPairs().stream().max(Comparator.comparing(Pair::getCounter))
                .orElseThrow(() -> new RuntimeException("Pas de gagnants."));
        // Récupérer les noms des étudiants qui composent le binôme
        String winnerNames = winner.getStudents().stream().map(s -> s.getFirstName() + " " + s.getLastName())
                .collect(Collectors.joining(" & "));

        String result = "Le binôme gagnant est :" + winnerNames + " avec " + winner.getCounter() + " voix.";

        // Envoyer le mail à tous les votants
        for (Voter voter : election.getVoters()) {
            String text = """
                    Bonjour,

                    %s

                    Cordialement,
                    L’équipe organisation
                    """.formatted(result);

            emailServiceImpl.sendSimpleMessage(voter.getEmail(), "Résultats de l’élection des délégués", text);
        }
    }
}
