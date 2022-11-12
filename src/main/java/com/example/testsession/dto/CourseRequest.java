package com.example.testsession.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseRequest {
    private String courseName;
    private String description;
    private String image;
}
