package com.example.testsession.api;

import com.example.testsession.dto.AuthResponse;
import com.example.testsession.dto.LoginRequest;
import com.example.testsession.model.Course;
import com.example.testsession.model.Role;
import com.example.testsession.model.User;
import com.example.testsession.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class UserApiTest {

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
    void login() {
        String url = "http://localhost:" + port + "/api/auth/login";

        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(
                url,
                new LoginRequest("nurisa@gmail.com", "nurisa"),
                AuthResponse.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String token = response.getBody().getToken();
        log.info("token {}",token);
        assertThat(token).isNotNull();
    }
}