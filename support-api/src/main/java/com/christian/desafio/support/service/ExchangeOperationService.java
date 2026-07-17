package com.christian.desafio.support.service;

import com.christian.desafio.common.dto.ExchangeResponse;
import com.christian.desafio.common.dto.SupportExchangeRequest;
import com.christian.desafio.support.domain.ExchangeOperation;
import com.christian.desafio.support.repository.ExchangeOperationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExchangeOperationService {
    private final ExchangeOperationRepository repository;

    public ExchangeOperationService(ExchangeOperationRepository repository) {
        this.repository = repository;
    }

    /** Persiste la operación y conserva los datos de auditoría solicitados. */
    public Mono<ExchangeResponse> create(SupportExchangeRequest request) {
        return repository.save(toEntity(request, null)).map(this::toResponse);
    }

    /**
     * Busca primero el registro para evitar actualizar un ID inexistente y
     * conserva el mismo identificador al guardar la nueva información.
     */
    public Mono<ExchangeResponse> update(Long id, SupportExchangeRequest request) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro no encontrado")))
                .flatMap(existing -> repository.save(toEntity(request, existing.id())))
                .map(this::toResponse);
    }

    /** La búsqueda por ID utiliza la clave primaria de la tabla. */
    public Mono<ExchangeResponse> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro no encontrado")))
                .map(this::toResponse);
    }

    /** Devuelve un Flux para transmitir los registros sin cargar una lista manualmente. */
    public Flux<ExchangeResponse> findAll() {
        return repository.findAll().map(this::toResponse);
    }

    private ExchangeOperation toEntity(SupportExchangeRequest request, Long id) {
        return new ExchangeOperation(id, request.userId(), request.userName(), request.initialAmount(),
                request.finalAmount(), request.sourceCurrency(), request.targetCurrency(),
                request.exchangeRate(), request.processedAt());
    }

    private ExchangeResponse toResponse(ExchangeOperation operation) {
        return new ExchangeResponse(operation.id(), operation.userId(), operation.userName(),
                operation.initialAmount(), operation.finalAmount(), operation.sourceCurrency(),
                operation.targetCurrency(), operation.exchangeRate(), operation.processedAt());
    }
}
