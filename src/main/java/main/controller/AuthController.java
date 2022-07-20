package main.controller;

import main.dto.JwtRequestDTO;
import main.dto.JwtResponseDTO;
import main.security.jwt.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> auth(@RequestBody JwtRequestDTO authRequest) throws AuthException {
        final JwtResponseDTO token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }
}
