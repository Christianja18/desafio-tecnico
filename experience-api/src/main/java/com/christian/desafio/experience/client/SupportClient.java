package com.christian.desafio.experience.client;

import com.christian.desafio.common.dto.ExchangeResponse;
import com.christian.desafio.common.dto.SupportExchangeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class SupportClient {
    private final WebClient webClient;

    public SupportClient(WebClient supportWebClient) {
        this.webClient = supportWebClient;
    }

    /**
     * Envía el registro ya enriquecido con el nombre obtenido desde GoREST.
     * WebClient mantiene esta comunicación dentro del flujo reactivo.
     */
    public Mono<ExchangeResponse> create(SupportExchangeRequest request) {
        return webClient.post()
                .uri("/api/v1/exchanges")
                .bodyValue(request)
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> Mono.error(new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                                "No fue posible completar la operación en API Support")))
                .bodyToMono(ExchangeResponse.class)
                .onErrorMap(WebClientRequestException.class,
                        exception -> new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                                "No fue posible conectar con API Support"));
    }
}
