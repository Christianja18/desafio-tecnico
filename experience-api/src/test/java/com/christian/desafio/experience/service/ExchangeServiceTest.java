package com.christian.desafio.experience.service;

import com.christian.desafio.common.dto.ExchangeRequest;
import com.christian.desafio.common.dto.ExchangeResponse;
import com.christian.desafio.experience.client.GorestClient;
import com.christian.desafio.experience.client.GorestUser;
import com.christian.desafio.experience.client.SupportClient;
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
class ExchangeServiceTest {
    @Mock
    private GorestClient gorestClient;

    @Mock
    private SupportClient supportClient;

    @InjectMocks
    private ExchangeService service;

    @Test
    void shouldValidateUserCalculateAmountAndSendOperationToSupport() {
        ExchangeRequest request = new ExchangeRequest(10L, new BigDecimal("100.00"),
                "USD", "PEN", new BigDecimal("3.75"));
        ExchangeResponse expected = new ExchangeResponse(1L, 10L, "Jane Doe",
                request.amount(), new BigDecimal("375.0000"), "USD", "PEN",
                request.exchangeRate(), LocalDateTime.now());

        when(gorestClient.findUser(10L)).thenReturn(Mono.just(new GorestUser(10L, "Jane Doe")));
        when(supportClient.create(any())).thenReturn(Mono.just(expected));

        StepVerifier.create(service.exchange(request))
                .assertNext(response -> assertThat(response.finalAmount()).isEqualByComparingTo("375.0000"))
                .verifyComplete();

        verify(gorestClient).findUser(10L);
        ArgumentCaptor<com.christian.desafio.common.dto.SupportExchangeRequest> captor =
                ArgumentCaptor.forClass(com.christian.desafio.common.dto.SupportExchangeRequest.class);
        verify(supportClient).create(captor.capture());
        assertThat(captor.getValue().userName()).isEqualTo("Jane Doe");
        assertThat(captor.getValue().finalAmount()).isEqualByComparingTo("375.00");
    }

    @Test
    void shouldNotCallSupportWhenUserDoesNotExist() {
        ExchangeRequest request = new ExchangeRequest(99L, new BigDecimal("100.00"),
                "USD", "PEN", new BigDecimal("3.75"));
        RuntimeException error = new RuntimeException("user not found");
        when(gorestClient.findUser(99L)).thenReturn(Mono.error(error));

        StepVerifier.create(service.exchange(request))
                .expectErrorMatches(error::equals)
                .verify();

        verifyNoInteractions(supportClient);
    }
}
