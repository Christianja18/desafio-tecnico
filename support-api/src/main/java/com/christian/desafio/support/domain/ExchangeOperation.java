package com.christian.desafio.support.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("exchange_operations")
public record ExchangeOperation(
        @Id Long id,
        @Column("user_id") Long userId,
        @Column("user_name") String userName,
        @Column("initial_amount") BigDecimal initialAmount,
        @Column("final_amount") BigDecimal finalAmount,
        @Column("source_currency") String sourceCurrency,
        @Column("target_currency") String targetCurrency,
        @Column("exchange_rate") BigDecimal exchangeRate,
        @Column("processed_at") LocalDateTime processedAt) {
}
