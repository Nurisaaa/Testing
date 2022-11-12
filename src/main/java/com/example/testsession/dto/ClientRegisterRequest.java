package com.example.testsession.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
@AllArgsConstructor
@Valid
public class ClientRegisterRequest {

    private String email;

    private String password;
}