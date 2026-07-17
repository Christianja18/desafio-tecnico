package com.christian.desafio.experience.service;

import com.christian.desafio.common.dto.ExchangeRequest;
import com.christian.desafio.common.dto.ExchangeResponse;
import com.christian.desafio.common.dto.SupportExchangeRequest;
import com.christian.desafio.experience.client.GorestClient;
import com.christian.desafio.experience.client.SupportClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ExchangeService {
    private final GorestClient gorestClient;
    private final SupportClient supportClient;

    public ExchangeService(GorestClient gorestClient, SupportClient supportClient) {
        this.gorestClient = gorestClient;
        this.supportClient = supportClient;
    }

    /**
     * Orquesta el caso de uso: primero valida el usuario en GoREST y solo
     * después solicita a Support la persistencia. Al usar Mono no se bloquea
     * el hilo mientras se espera una respuesta HTTP externa.
     */
    public Mono<ExchangeResponse> exchange(ExchangeRequest request) {
        return gorestClient.findUser(request.userId())
                .map(user -> new SupportExchangeRequest(
                        request.userId(),
                        user.name(),
                        request.amount(),
                        request.amount().multiply(request.exchangeRate()),
                        request.sourceCurrency(),
                        request.targetCurrency(),
                        request.exchangeRate(),
                        LocalDateTime.now()))
                .flatMap(supportClient::create);
    }
}
