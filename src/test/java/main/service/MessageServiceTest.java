package main.service;

import main.model.Message;
import main.model.User;
import main.repositories.MessageRepository;
import main.security.jwt.JwtFilter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@WebMvcTest(MessageService.class)
public class MessageServiceTest {

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private JwtFilter jwtFilter;

    @Test
    void getMyMessages() {
        User user = new User();
        user.setUserId(500);
        Optional<List<Message>> messages = messageRepository.findMessageByUserId(user.getUserId());
        assertFalse(messages.isPresent());
    }
}
