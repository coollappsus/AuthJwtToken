package main.service;

import main.model.User;
import main.repositories.UserRepository;
import main.security.jwt.JwtFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.assertFalse;

@WebMvcTest(UserService.class)
public class UserServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtFilter jwtFilter;

    @Test
    void getUserByLogin() {
        Optional<User> userOptional = userRepository.findUserByLogin("dima");
        assertFalse(userOptional.isPresent());
    }
}
