package com.example.testsession;

import com.example.testsession.model.Course;
import com.example.testsession.repositories.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @AfterEach
    void tearDown() {
        courseRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void saveCourse() {
        Course course = new Course("Java",
                "java is the best",
                "image");
        courseRepository.save(course);

        log.info("course id {} ", course.getId());
        assertEquals(courseRepository.findById(course.getId()).get(), course);
        assertThat(course.getId()).isGreaterThan(0);
    }

    @Test
    @Sql(scripts = "/scripts/add_courses.sql")
    @Order(2)
    public void getById() {
        assertEquals(courseRepository.findById(1L).get().getId(), 1L);
    }

    @Test
    @Order(3)
    @Sql(scripts = "/scripts/add_courses.sql")
    public void findAll() {
        List<Course> courses = courseRepository.findAll();
        assertThat(courses.size()).isGreaterThan(0);
    }

    @Test
    @Sql(scripts = "/scripts/add_courses.sql")
    public void update() {
        Course course = courseRepository.findById(1L).get();
        course.setCourseName("Python");
        Course updatedCourse = courseRepository.save(course);

        assertEquals(updatedCourse.getCourseName(), "Python");
    }

    @Test
    @Sql(scripts = "/scripts/add_courses.sql")
    public void deleteTest(){
        Course course = courseRepository.findById(1L).get();
        courseRepository.delete(course);

        assertThat(courseRepository.findAll().size()).isLessThan(3);
    }
}
