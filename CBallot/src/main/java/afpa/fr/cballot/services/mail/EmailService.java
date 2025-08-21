package afpa.fr.cballot.services.mail;

import afpa.fr.cballot.entities.Election;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);

    void sendVoteInvitationEmails(Election election);

    void sendResultEmails(Election election);
}