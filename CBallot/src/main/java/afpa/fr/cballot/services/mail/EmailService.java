package afpa.fr.cballot.services.mail;

import afpa.fr.cballot.entities.Election;

public interface EmailService {
    void sendVoteInvitationEmails(Election election);

    void sendResultEmails(Election election);
}