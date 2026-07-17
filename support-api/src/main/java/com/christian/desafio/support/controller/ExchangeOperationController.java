package com.christian.desafio.support.controller;

import com.christian.desafio.common.dto.ExchangeResponse;
import com.christian.desafio.common.dto.SupportExchangeRequest;
import com.christian.desafio.support.service.ExchangeOperationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/exchanges")
public class ExchangeOperationController {
    private final ExchangeOperationService service;

    public ExchangeOperationController(ExchangeOperationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    /** El estado 201 identifica que el registro fue creado correctamente. */
    public Mono<ExchangeResponse> create(@Valid @RequestBody SupportExchangeRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public Mono<ExchangeResponse> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Flux<ExchangeResponse> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public Mono<ExchangeResponse> update(@PathVariable Long id,
                                         @Valid @RequestBody SupportExchangeRequest request) {
        return service.update(id, request);
    }
}
