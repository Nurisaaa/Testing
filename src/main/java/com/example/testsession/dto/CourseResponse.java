package com.example.testsession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CourseResponse {
    private Long id;
    private String courseName;
    private String description;
    private String image;
}
