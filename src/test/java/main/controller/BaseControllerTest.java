package main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.dto.RequestDTO;
import main.model.User;
import main.security.jwt.AuthService;
import main.security.jwt.JwtFilter;
import main.security.jwt.JwtProvider;
import main.service.MessageService;
import main.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseControllerTest {

    private static final String jwtSecret =
            "qBTmv4oXFFR2GwjexDJ4t6fsIUIUhhXqlktXjXdkcyygs8nPVEwMfo29VDRRepYDVV5IkIxBMzr7OEHXEHd37w==";

    @Autowired
    private JwtProvider tokenProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.tokenProvider = new JwtProvider(jwtSecret);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    @MockBean
    private MessageService messageService;

    @MockBean
    private JwtFilter jwtFilter;

    @Test
    public void message() throws Exception {
        String token = tokenProvider.generateToken(
                new User("ivan", "ivan", "ivan", "ivan"));
        assertNotNull(token);

        mockMvc.perform(post("/api/message").header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RequestDTO("alex", "test"))))
                .andExpect(status().isOk());
    }

    @Test
    public void getMyMessages() throws Exception {

        String token = tokenProvider.generateToken(
                new User("ivan", "ivan", "ivan", "ivan"));
        assertNotNull(token);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/message/auth")
                .header("Authorization", token)).andExpect(status().isOk());
    }
}
