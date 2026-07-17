package com.christian.desafio.common.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExchangeResponse(
        Long id,
        Long userId,
        String userName,
        BigDecimal initialAmount,
        BigDecimal finalAmount,
        String sourceCurrency,
        String targetCurrency,
        BigDecimal exchangeRate,
        LocalDateTime processedAt) {
}
