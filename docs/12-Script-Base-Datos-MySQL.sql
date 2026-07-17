-- Script MySQL para el desafío técnico
--
-- La auditoría funcional se registra en la misma tabla del proceso de cambio,
-- conforme al requerimiento: nombre de usuario, monto inicial, monto final,
-- tipo de cambio y fecha del proceso.
--
-- No se crea una tabla de usuarios porque el usuario se valida mediante GoREST.
-- No se crea una tabla de auditoría independiente porque el requerimiento no la exige.
CREATE DATABASE IF NOT EXISTS exchange_db;

USE exchange_db;
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

-- Datos de prueba
-- Este bloque agrega 15 registros para probar registro y busqueda.
INSERT INTO exchange_operations (
    user_id,
    user_name,
    initial_amount,
    final_amount,
    source_currency,
    target_currency,
    exchange_rate,
    processed_at
) VALUES
    (1,  'Usuario Prueba 01', 100.00,  375.00,  'USD', 'PEN', 3.75000000, '2026-07-01 09:00:00'),
    (2,  'Usuario Prueba 02', 250.00,  937.50,  'USD', 'PEN', 3.75000000, '2026-07-01 09:15:00'),
    (3,  'Usuario Prueba 03', 50.00,   187.50,  'USD', 'PEN', 3.75000000, '2026-07-01 09:30:00'),
    (4,  'Usuario Prueba 04', 80.00,   300.00,  'USD', 'PEN', 3.75000000, '2026-07-01 10:00:00'),
    (5,  'Usuario Prueba 05', 120.00,  450.00,  'USD', 'PEN', 3.75000000, '2026-07-01 10:30:00'),
    (6,  'Usuario Prueba 06', 1000.00, 3750.00, 'USD', 'PEN', 3.75000000, '2026-07-01 11:00:00'),
    (7,  'Usuario Prueba 07', 75.00,   281.25,  'USD', 'PEN', 3.75000000, '2026-07-01 11:30:00'),
    (8,  'Usuario Prueba 08', 300.00,  1125.00, 'USD', 'PEN', 3.75000000, '2026-07-01 12:00:00'),
    (9,  'Usuario Prueba 09', 45.00,   168.75,  'USD', 'PEN', 3.75000000, '2026-07-02 09:00:00'),
    (10, 'Usuario Prueba 10', 200.00,  760.00,  'USD', 'PEN', 3.80000000, '2026-07-02 09:30:00'),
    (11, 'Usuario Prueba 11', 150.00,  570.00,  'USD', 'PEN', 3.80000000, '2026-07-02 10:00:00'),
    (12, 'Usuario Prueba 12', 90.00,   342.00,  'USD', 'PEN', 3.80000000, '2026-07-02 10:30:00'),
    (13, 'Usuario Prueba 13', 500.00,  1900.00, 'USD', 'PEN', 3.80000000, '2026-07-02 11:00:00'),
    (14, 'Usuario Prueba 14', 60.00,   228.00,  'USD', 'PEN', 3.80000000, '2026-07-02 11:30:00'),
    (15, 'Usuario Prueba 15', 400.00,  1520.00, 'USD', 'PEN', 3.80000000, '2026-07-02 12:00:00');
