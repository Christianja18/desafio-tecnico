package com.christian.desafio.experience.controller;

import com.christian.desafio.common.dto.LoginRequest;
import com.christian.desafio.common.dto.LoginResponse;
import com.christian.desafio.experience.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/login")
public class AuthController {
    private final JwtService jwtService;
    private final String configuredUsername;
    private final String configuredPassword;

    public AuthController(JwtService jwtService,
                          @Value("${app.auth.username}") String configuredUsername,
                          @Value("${app.auth.password}") String configuredPassword) {
        this.jwtService = jwtService;
        this.configuredUsername = configuredUsername;
        this.configuredPassword = configuredPassword;
    }

    @PostMapping
    public Mono<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        if (!configuredUsername.equals(request.username()) || !configuredPassword.equals(request.password())) {
            return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));
        }
        return Mono.just(new LoginResponse(jwtService.generate(request.username())));
    }
}
