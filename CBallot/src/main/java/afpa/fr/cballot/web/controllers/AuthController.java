package afpa.fr.cballot.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import afpa.fr.cballot.dtos.LoginDTO;
import afpa.fr.cballot.services.authentication.AuthService;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody Map<String, String> userLogs) {
        System.out.println(userLogs);

        LoginDTO loginDto = authService.createToken(userLogs);

        // + renvoyer cookie + http only 
        return new ResponseEntity<>(loginDto, HttpStatus.CREATED);
    }

}
