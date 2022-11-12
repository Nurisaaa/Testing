package com.example.testsession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NotBlank
@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
