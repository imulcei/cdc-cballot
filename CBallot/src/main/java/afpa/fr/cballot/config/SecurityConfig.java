package afpa.fr.cballot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import afpa.fr.cballot.services.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // private final AuthenticationProvider authenticationProvider;
    // private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // public SecurityConfig(AuthenticationProvider authenticationProvider,
    //         JwtAuthenticationFilter jwtAuthenticationFilter) {
    //     this.authenticationProvider = authenticationProvider;
    //     this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    // }

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactiver CSRF proprement
                .csrf(AbstractHttpConfigurer::disable)

                // Gestion des règles d'autorisation
                .authorizeHttpRequests(auth -> auth

                        // Endpoints public : accessible par tous
                        .requestMatchers("/api/auth/**").permitAll()

                        // Endpoints accessibles uniquement par l'ADMIN et TEACHER
                        .requestMatchers("/api/sessions/**").hasAnyRole("TEACHER", "ADMIN")
                        .requestMatchers("/api/voter/**").hasAnyRole("TEACHER", "ADMIN", "VOTER")
                        .requestMatchers("/api/election/**").hasAnyRole("TEACHER", "ADMIN")

                        // Endpoints accessibles uniquement par l'ADMIN
                        .requestMatchers("/api/**").hasRole("ADMIN")

                        // Tout le reste nécessite d'être authentifié
                        // .anyRequest().authenticated()
                )

                // Activer Basic Auth de façon moderne
                // .httpBasic(Customizer.withDefaults());

                .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider aauthAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider()
    }
}
