package com.example.testsession;

import com.example.testsession.model.Course;
import com.example.testsession.repositories.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@Slf4j
public class CourseRepoTest {

    @Autowired
    private CourseRepository courseRepository;

//    @BeforeEach
//    void setUp() {
//        courseRepository.save(new Course("Js ",
//                "image",
//                "JavaScript"));
//    }
//
//    @AfterEach
//    void tearDown() {
//        courseRepository.deleteAll();
//    }

    @Test
    @Order(1)
    void saveTest() {
        int before = courseRepository.findAll().size(); // 0
        courseRepository.save(new Course("Java is the best",
                "image",
                "Java"));
        int after = courseRepository.findAll().size(); // 1
        assertThat(before).isLessThan(after);
    }

    @Test
    @Sql(scripts = "/scripts/courses.sql")
    @Order(2)
    void getById() {
        Course course = courseRepository.findById(1L).get();
        assertEquals(course.getId(), 1L);
        Course course1 = courseRepository.findById(2L).get();
        assertEquals(course1.getId(), 2L);
        Course course2 = courseRepository.findById(3L).get();
        assertEquals(course2.getId(), 3L);
    }

    @Test
    @Sql(scripts = "/scripts/courses.sql")
    @Order(3)
    void updateTest() {
        Course course = courseRepository.findById(1L).get();
        course.setCourseName("Python");
        Course savedCourse = courseRepository.save(course);
        log.info("course name  = {}", savedCourse.getCourseName());
        assertEquals(
                savedCourse.getCourseName(),
                "Python"
        );
    }

    @Test
    @Sql(scripts = "/scripts/courses.sql")
    @Order(4)
    void findAll(){
        int size = courseRepository.findAll().size();
        assertThat(size).isGreaterThan(0);
    }

    @Test
    @Sql(scripts = "/scripts/courses.sql")
    @Order(5)
    void delete(){
        int before = courseRepository.findAll().size();
        Course course = courseRepository.findById(1L).get();
        courseRepository.delete(course);
        int after = courseRepository.findAll().size();
        assertThat(before).isGreaterThan(after);
    }
}
