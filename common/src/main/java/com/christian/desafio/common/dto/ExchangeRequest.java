package com.christian.desafio.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ExchangeRequest(
        @NotNull Long userId,
        @NotNull BigDecimal amount,
        @NotBlank String sourceCurrency,
        @NotBlank String targetCurrency,
        @NotNull BigDecimal exchangeRate) {
}
