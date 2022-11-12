package com.example.testsession.model;

import com.example.testsession.dto.CourseRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "course_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String courseName;

    @Column(length = 10000)
    private String courseDescription;

    private String courseImage;

    public Course(CourseRequest request) {
        this.courseName = request.getCourseName();
        this.courseDescription = request.getDescription();
        this.courseImage = request.getImage();
    }

    public Course(String courseName, String courseDescription, String courseImage) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseImage = courseImage;
    }
}
