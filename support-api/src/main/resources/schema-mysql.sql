CREATE TABLE IF NOT EXISTS exchange_operations (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    initial_amount DECIMAL(19, 4) NOT NULL,
    final_amount DECIMAL(19, 4) NOT NULL,
    source_currency VARCHAR(10) NOT NULL,
    target_currency VARCHAR(10) NOT NULL,
    exchange_rate DECIMAL(19, 8) NOT NULL,
    processed_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);
