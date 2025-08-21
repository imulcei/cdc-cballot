package afpa.fr.cballot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // désactive CSRF (utile pour tester avec Insomnia/Postman)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll() // autorise tout sur /api/**
                .anyRequest().authenticated()
            )
            .httpBasic(); // active l'authentification basique (Authorization: Basic xxx)

        return http.build();
    }
}

