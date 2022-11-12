package com.example.testsession.service;

import com.example.testsession.dto.CourseRequest;
import com.example.testsession.dto.CourseResponse;
import com.example.testsession.dto.SimpleResponse;
import com.example.testsession.exception.NotFoundException;
import com.example.testsession.model.Course;
import com.example.testsession.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseResponse createCourse(CourseRequest request) {
        Course course = courseRepository.save(new Course(request));
        return courseRepository.getCourseById(course.getId());
    }

    public SimpleResponse deleteById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Курс не найден"));
        courseRepository.delete(course);
        return new SimpleResponse("Курс удалён");
    }

    public CourseResponse getById(Long id) {
        return courseRepository.getCourseById(id);
    }

    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Курс не найден"));
        courseRepository.update(
                course.getId(),
                request.getCourseName(),
                request.getDescription(),
                request.getImage());
        return new CourseResponse(
                course.getId(),
                request.getCourseName(),
                request.getDescription(),
                request.getImage());
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.getAllCourses();
    }
}