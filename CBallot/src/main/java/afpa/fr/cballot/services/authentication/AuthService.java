package afpa.fr.cballot.services.authentication;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import afpa.fr.cballot.entities.entityuserdetails.AdminUserDetails;
import afpa.fr.cballot.entities.entityuserdetails.TeacherUserDetails;
import afpa.fr.cballot.repositories.AdminRepository;
import afpa.fr.cballot.repositories.TeacherRepository;
import afpa.fr.cballot.services.security.JwtService;

@Service
public class AuthService {

    private AuthenticationManager authenticationManager;
    private AdminRepository adminRepository;
    private TeacherRepository teacherRepository;
    private JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, AdminRepository adminRepository,
            TeacherRepository teacherRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
        this.jwtService = jwtService;
    }

    public String createToken(Map<String, String> login) {
        String email = login.get("email");
        System.out.println(email);
        String password = login.get("password");
        System.out.println(password);

        // On tente d'authentifier
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        System.out.println("j'arrive jusqu'ici");

        // Vérifie Admin
        return adminRepository.findByEmail(email)
                .map(AdminUserDetails::new)
                .map(jwtService::generateToken)

                // Sinon Teacher
                .orElseGet(() -> teacherRepository.findByEmail(email)
                        .map(TeacherUserDetails::new)
                        .map(jwtService::generateToken)
                        .orElseThrow(() -> new RuntimeException("User not found : " + email)));
    }
}
