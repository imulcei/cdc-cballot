package afpa.fr.cballot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import afpa.fr.cballot.services.mail.EmailService;

@Component
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("noreply@afpa-test-cda.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }
}
