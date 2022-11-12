package com.example.testsession.service;

import com.example.testsession.dto.AuthResponse;
import com.example.testsession.dto.ClientRegisterRequest;
import com.example.testsession.dto.ClientRegisterResponse;
import com.example.testsession.dto.LoginRequest;
import com.example.testsession.exception.BadCredentialsException;
import com.example.testsession.model.User;
import com.example.testsession.repositories.UserRepository;
import com.example.testsession.security.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;


    public AuthResponse login(LoginRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getEmail(),
                        userRequest.getPassword()));
        System.out.println(userRequest.getEmail());
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new BadCredentialsException("Неправильные данные"));
        String token = jwtTokenUtil.generateToken(user.getEmail());
        return new AuthResponse(user.getUsername(),token,user.getRole());
    }

    public ClientRegisterResponse register(ClientRegisterRequest clientRegisterRequest) {

        clientRegisterRequest.setPassword(passwordEncoder.encode(clientRegisterRequest.getPassword()));

        User user = new User(clientRegisterRequest);

        User user1 = userRepository.save(user);

        String token = jwtTokenUtil.generateToken(user1.getEmail());

        return new ClientRegisterResponse(
                user1.getEmail(),
                token,
                user1.getRole());
    }
}