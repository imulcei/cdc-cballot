package afpa.fr.cballot.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.Customizer;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import afpa.fr.cballot.entities.entityuserdetails.AdminUserDetails;
import afpa.fr.cballot.entities.entityuserdetails.TeacherUserDetails;
import afpa.fr.cballot.repositories.AdminRepository;
import afpa.fr.cballot.repositories.TeacherRepository;
import afpa.fr.cballot.services.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(AdminRepository adminRepository,
            TeacherRepository teacherRepository,
            JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationProvider authenticationProvider)
            throws Exception {
        http
                // Active la config CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // Désactiver CSRF proprement
                .csrf(AbstractHttpConfigurer::disable)

                // Gestion des règles d'autorisation
                .authorizeHttpRequests(auth -> auth

                        // Endpoints public : accessible par tous
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/voter/**").permitAll()

                        // Endpoints accessibles uniquement par l'ADMIN et TEACHER
                        .requestMatchers("/api/sessions/**").hasAnyRole("TEACHER", "ADMIN")
                        .requestMatchers("/api/election/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                        // .requestMatchers("/api/election/**").hasAnyRole("TEACHER", "ADMIN")

                        // Endpoints accessibles uniquement par l'ADMIN
                        .requestMatchers("/api/**").hasRole("ADMIN")

                        // Tout le reste nécessite d'être authentifié
                        .anyRequest().authenticated())

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(401);
                            response.setContentType("application/json");
                            response.getWriter().write(
                                    "{\"error\":\"Unauthorized\",\"message\":\""
                                            + authException.getMessage()
                                            + "\"}");
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
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
