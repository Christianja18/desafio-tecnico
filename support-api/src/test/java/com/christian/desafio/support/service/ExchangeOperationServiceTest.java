package com.christian.desafio.support.service;

import com.christian.desafio.common.dto.SupportExchangeRequest;
import com.christian.desafio.support.domain.ExchangeOperation;
import com.christian.desafio.support.repository.ExchangeOperationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeOperationServiceTest {
    @Mock
    private ExchangeOperationRepository repository;

    @InjectMocks
    private ExchangeOperationService service;

    private final SupportExchangeRequest request = new SupportExchangeRequest(
            10L, "Jane Doe", new BigDecimal("100.00"), new BigDecimal("375.00"),
            "USD", "PEN", new BigDecimal("3.75"), LocalDateTime.of(2026, 1, 1, 10, 0));

    @Test
    void shouldPersistAndReturnOperation() {
        ExchangeOperation saved = new ExchangeOperation(1L, request.userId(), request.userName(),
                request.initialAmount(), request.finalAmount(), request.sourceCurrency(),
                request.targetCurrency(), request.exchangeRate(), request.processedAt());
        when(repository.save(any())).thenReturn(Mono.just(saved));

        StepVerifier.create(service.create(request))
                .assertNext(response -> {
                    assertThat(response.id()).isEqualTo(1L);
                    assertThat(response.userName()).isEqualTo("Jane Doe");
                })
                .verifyComplete();

        ArgumentCaptor<ExchangeOperation> captor = ArgumentCaptor.forClass(ExchangeOperation.class);
        verify(repository).save(captor.capture());
        assertThat(captor.getValue().userId()).isEqualTo(10L);
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingMissingOperation() {
        when(repository.findById(20L)).thenReturn(Mono.empty());

        StepVerifier.create(service.update(20L, request))
                .expectErrorMatches(error -> error instanceof org.springframework.web.server.ResponseStatusException
                        && ((org.springframework.web.server.ResponseStatusException) error).getStatusCode().value() == 404)
                .verify();

        verify(repository, never()).save(any());
    }
}
