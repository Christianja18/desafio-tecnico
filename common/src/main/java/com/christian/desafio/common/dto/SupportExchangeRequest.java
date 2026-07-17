package com.christian.desafio.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SupportExchangeRequest(
        @NotNull Long userId,
        @NotBlank String userName,
        @NotNull BigDecimal initialAmount,
        @NotNull BigDecimal finalAmount,
        @NotBlank String sourceCurrency,
        @NotBlank String targetCurrency,
        @NotNull BigDecimal exchangeRate,
        @NotNull LocalDateTime processedAt) {
}
