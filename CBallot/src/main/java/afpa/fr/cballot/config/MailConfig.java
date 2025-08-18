package afpa.fr.cballot.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

// https://github.com/cdimascio/dotenv-java
import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class MailConfig {

    /**
     * Paramètre le serveur Gmail dans Spring Boot.
     * 
     * @return Les paramètres et les identifiants de connexion au serveur Gmail.
     */
    @Bean
    public JavaMailSender getJavaMailSender() {
        Dotenv dotenv = Dotenv.load();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("imulcei@gmail.com");
        // pour éviter le password en dur, on le stocke dans un variable .env et on
        // utilise Dotenv pour aller chercher la valeur dans le .env
        mailSender.setPassword(dotenv.get("EMAIL_PASSWORD"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
