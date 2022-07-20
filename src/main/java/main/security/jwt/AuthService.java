package main.security.jwt;

import main.dto.JwtRequestDTO;
import main.dto.JwtResponseDTO;
import main.model.User;
import main.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public AuthService(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    public JwtResponseDTO login(JwtRequestDTO authRequest) throws AuthException {
        final User user = userService.getByLogin(authRequest.getLogin());

        if (user.getPassword().equals(authRequest.getPassword())) { // если пароль верен
            final String accessToken = jwtProvider.generateToken(user); //то генерируем токен
            return new JwtResponseDTO(accessToken); //возвращаем новый токен
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
