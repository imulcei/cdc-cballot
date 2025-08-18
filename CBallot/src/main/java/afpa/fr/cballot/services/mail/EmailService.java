package afpa.fr.cballot.services.mail;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
}