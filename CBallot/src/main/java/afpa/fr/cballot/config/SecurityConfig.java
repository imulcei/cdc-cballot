package afpa.fr.cballot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactiver CSRF proprement
                .csrf(AbstractHttpConfigurer::disable)

                // Gestion des règles d'autorisation
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // autoriser tout pour /api/**
                        .anyRequest().authenticated())

                // Activer Basic Auth de façon moderne
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
