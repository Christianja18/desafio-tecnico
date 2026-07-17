package com.christian.desafio.experience.client;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class GorestClient {
    private final WebClient webClient;

    public GorestClient(WebClient gorestWebClient) {
        this.webClient = gorestWebClient;
    }

    /**
     * Consulta el usuario en el endpoint público de GoREST. Los errores 404
     * se convierten en un error de negocio controlado y los demás errores de
     * la dependencia se exponen como error de gateway.
     */
    public Mono<GorestUser> findUser(Long userId) {
        return webClient.get()
                .uri("/public/v2/users/{id}", userId)
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Usuario no encontrado en GoREST")))
                .onStatus(status -> status.isError(),
                        response -> Mono.error(new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                                "No fue posible validar el usuario en GoREST")))
                .bodyToMono(GorestUser.class)
                .onErrorMap(WebClientRequestException.class,
                        exception -> new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                                "No fue posible conectar con GoREST"));
    }
}
