package com.christian.desafio.experience.controller;

import com.christian.desafio.common.dto.ExchangeRequest;
import com.christian.desafio.common.dto.ExchangeResponse;
import com.christian.desafio.experience.service.ExchangeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/exchange")
public class ExchangeController {
    private final ExchangeService service;

    public ExchangeController(ExchangeService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<ExchangeResponse> exchange(@Valid @RequestBody ExchangeRequest request) {
        return service.exchange(request);
    }
}
