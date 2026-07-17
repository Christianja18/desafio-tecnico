package com.christian.desafio.experience.config;

import com.christian.desafio.common.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ErrorResponse> handleStatus(ResponseStatusException exception, ServerWebExchange exchange) {
        HttpStatus status = HttpStatus.valueOf(exception.getStatusCode().value());
        exchange.getResponse().setStatusCode(status);
        return Mono.just(new ErrorResponse(Instant.now(), status.value(), status.getReasonPhrase(),
                exception.getReason(), exchange.getRequest().getPath().value()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ErrorResponse> handleValidation(WebExchangeBindException exception, ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        return Mono.just(new ErrorResponse(Instant.now(), 400, "Bad Request",
                "Datos de entrada inválidos", exchange.getRequest().getPath().value()));
    }

    @ExceptionHandler(ServerWebInputException.class)
    public Mono<ErrorResponse> handleInput(ServerWebInputException exception, ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        return Mono.just(new ErrorResponse(Instant.now(), 400, "Bad Request",
                "Datos de entrada inválidos", exchange.getRequest().getPath().value()));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ErrorResponse> handleUnexpected(Exception exception, ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return Mono.just(new ErrorResponse(Instant.now(), 500, "Internal Server Error",
                "Error interno", exchange.getRequest().getPath().value()));
    }
}
