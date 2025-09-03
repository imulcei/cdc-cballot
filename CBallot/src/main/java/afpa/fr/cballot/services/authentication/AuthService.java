package afpa.fr.cballot.services.authentication;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.LoginDTO;
import afpa.fr.cballot.entities.Admin;
import afpa.fr.cballot.entities.Teacher;
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

    public LoginDTO createToken(Map<String, String> login) {
        String email = login.get("email");
        System.out.println(email);
        String password = login.get("password");
        System.out.println(password);

        // On tente d'authentifier
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        Optional<Admin> admin = adminRepository.findByEmail(email);
        UserDetails userDetails = null;

        if (admin.isPresent()) {
            userDetails = new AdminUserDetails(admin.get());
        } else { // cas du "Teacher" (soit Admin, soit Teacher, pas les deux)

            Optional<Teacher> teacher = teacherRepository.findByEmail(email);

            if (teacher.isPresent()) {
                userDetails = new TeacherUserDetails(teacher.get());
            } else {
                throw new RuntimeException("User not found : " + email);
            }
        }

        String jwt = jwtService.generateToken(userDetails);
        Optional<String> role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst();

        if (role.isPresent()) {
            return new LoginDTO(jwt, role.get());
        } else {
            throw new RuntimeException("Role undefined for: " + email);
        }

        // LoginDTO loginDto = new LoginDTO(jwt, );

        // Vérifie Admin
        // return adminRepository.findByEmail(email)
        // .map(AdminUserDetails::new)
        // .map(jwtService::generateToken)

        // // Sinon Teacher
        // .orElseGet(() -> teacherRepository.findByEmail(email)
        // .map(TeacherUserDetails::new)
        // .map(jwtService::generateToken)
        // .orElseThrow(() -> new RuntimeException("User not found : " + email)));
    }
}
