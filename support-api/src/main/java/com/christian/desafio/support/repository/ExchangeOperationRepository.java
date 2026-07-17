package com.christian.desafio.support.repository;

import com.christian.desafio.support.domain.ExchangeOperation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ExchangeOperationRepository extends ReactiveCrudRepository<ExchangeOperation, Long> {
}
