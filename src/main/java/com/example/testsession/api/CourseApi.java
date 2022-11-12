package com.example.testsession.api;

import com.example.testsession.dto.CourseRequest;
import com.example.testsession.dto.CourseResponse;
import com.example.testsession.dto.SimpleResponse;
import com.example.testsession.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseApi {

    private final CourseService courseService;

    @PostMapping
    public CourseResponse createCourse(@RequestBody CourseRequest request) {
        return courseService.createCourse(request);
    }

    @PutMapping("/update/{id}")
    public CourseResponse updateCourse(@PathVariable("id") Long id, @RequestBody CourseRequest request) {
        return courseService.updateCourse(id, request);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteCourse(@PathVariable("id") Long id) {
        return courseService.deleteById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id) {
        return courseService.getById(id);
    }
}