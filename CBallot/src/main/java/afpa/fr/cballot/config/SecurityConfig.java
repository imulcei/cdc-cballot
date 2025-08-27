package afpa.fr.cballot.config;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import afpa.fr.cballot.entities.entityuserdetails.AdminUserDetails;
import afpa.fr.cballot.entities.entityuserdetails.TeacherUserDetails;
import afpa.fr.cballot.repositories.AdminRepository;
import afpa.fr.cballot.repositories.TeacherRepository;
import afpa.fr.cballot.services.security.JwtAuthenticationFilter;
import afpa.fr.cballot.services.security.JwtService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final JwtService jwtService;

    public SecurityConfig(AdminRepository adminRepository,
            TeacherRepository teacherRepository,
            JwtService jwtService) {
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
        this.jwtService = jwtService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
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
                        .anyRequest().authenticated())

                // Activer Basic Auth de façon moderne
                // .httpBasic(Customizer.withDefaults());

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authAuthenticationProvider(userDetailsService(), passwordEncoder()))
                .addFilterBefore(jwtAuthenticationFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(401);
                            response.setContentType("application/json");
                            response.getWriter().write(
                                    "{\"error\":\"Unauthorized\",\"message\":\"" + authException.getMessage() + "\"}");
                        }));
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            return adminRepository.findByEmail(username)
                    .<UserDetails>map(AdminUserDetails::new)
                    .orElseGet(() -> teacherRepository.findByEmail(username)
                            .map(TeacherUserDetails::new)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found.")));
        };
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
    AuthenticationProvider authAuthenticationProvider(UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService,
            UserDetailsService userDetailsService) {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

}
