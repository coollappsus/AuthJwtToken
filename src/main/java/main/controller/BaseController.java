package main.controller;

import main.dto.RequestDTO;
import main.dto.ResponseDTO;
import main.model.User;
import main.security.jwt.AuthService;
import main.security.jwt.JwtAuthentication;
import main.service.MessageService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BaseController {

    private final AuthService authService;
    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public BaseController(AuthService authService, MessageService messageService, UserService userService) {
        this.authService = authService;
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/message")
    public ResponseEntity<List<ResponseDTO>> message(@RequestBody RequestDTO requestDTO) {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        if (authInfo.isAuthenticated()) { //если прошел аутентификацию
            if (requestDTO.getMessage().matches("^history [0-9]+$")) {
                return ResponseEntity.ok(messageService.getMessages(requestDTO.getMessage()).stream()
                        .map(m -> new ResponseDTO(m.getText())).collect(Collectors.toList()));
            }
            User user = userService.getByLogin(authInfo.getUsername().toLowerCase());
            messageService.saveMessage(user, requestDTO.getMessage());
            return ResponseEntity.ok(List.of(new ResponseDTO("Сообщение успешно сохранено")));
        } else {
            return ResponseEntity.ok(List.of(new ResponseDTO("Обновите токен")));
        }
    }

    @GetMapping("/message/auth")
    public ResponseEntity<List<ResponseDTO>> getMyMessages() {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        if (authInfo.isAuthenticated()) {
            User user = userService.getByLogin(authInfo.getUsername().toLowerCase());
            return ResponseEntity.ok(messageService.getMyMessages(user).stream()
                    .map(m -> new ResponseDTO(m.getText())).collect(Collectors.toList()));
        } else {
            return ResponseEntity.ok(List.of(new ResponseDTO("Обновите токен")));
        }
    }
}
