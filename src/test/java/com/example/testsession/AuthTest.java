package com.example.testsession;

import com.example.testsession.dto.AuthResponse;
import com.example.testsession.dto.LoginRequest;
import com.example.testsession.model.Role;
import com.example.testsession.model.User;
import com.example.testsession.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles
public class AuthTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository.save(new User("nurisa@gmail.com",
                passwordEncoder.encode("nurisa"),
                Role.USER));
    }

    @Test
    void testAuth() {
        String url = "http://localhost:" + port + "/api/auth/login";
        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(
                url,
                new LoginRequest("nurisa@gmail.com", "nurisa"),
                AuthResponse.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
