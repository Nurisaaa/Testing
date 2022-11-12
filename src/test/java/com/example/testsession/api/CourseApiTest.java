package com.example.testsession.api;

import com.example.testsession.dto.CourseRequest;
import com.example.testsession.dto.CourseResponse;
import com.example.testsession.repositories.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class CourseApiTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void createCourse() {
        String url = "http://localhost:" + port + "/api/course";

        int before = courseRepository.findAll().size();
        ResponseEntity<CourseResponse> courseResponseResponseEntity = restTemplate.postForEntity(
                url,
                new CourseRequest(),
                CourseResponse.class);

        int after = courseRepository.findAll().size();
        assertEquals(HttpStatus.OK, courseResponseResponseEntity.getStatusCode());
        assertThat(after).isGreaterThan(before);
    }
}