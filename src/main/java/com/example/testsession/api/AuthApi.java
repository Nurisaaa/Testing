package com.example.testsession.api;
import com.example.testsession.dto.AuthResponse;
import com.example.testsession.dto.ClientRegisterRequest;
import com.example.testsession.dto.ClientRegisterResponse;
import com.example.testsession.dto.LoginRequest;
import com.example.testsession.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthApi {
    private final UserService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

    @PostMapping("/register")
    public ClientRegisterResponse register(@RequestBody @Valid ClientRegisterRequest clientRegisterRequest) {
        return authService.register(clientRegisterRequest);
    }
}
