# Modelo de datos

El modelo conserva los datos indicados en el enunciado. El mismo registro representa el proceso de cambio y la auditoria funcional asociada.

## Tabla `exchange_operations`

| Campo | Tipo MySQL | Obligatorio | Descripcion |
|---|---|---:|---|
| id | BIGINT AUTO_INCREMENT | Si | Identificador del registro. |
| user_id | BIGINT | Si | ID recibido y validado mediante GoREST. |
| user_name | VARCHAR(255) | Si | Nombre obtenido desde GoREST. |
| initial_amount | DECIMAL(19,4) | Si | Monto inicial. |
| final_amount | DECIMAL(19,4) | Si | Monto final calculado. |
| source_currency | VARCHAR(10) | Si | Moneda de origen. |
| target_currency | VARCHAR(10) | Si | Moneda de destino. |
| exchange_rate | DECIMAL(19,8) | Si | Tipo de cambio utilizado. |
| processed_at | DATETIME | Si | Fecha y hora del proceso. |

La entidad reactiva `ExchangeOperation` mapea estas columnas. H2 utiliza tipos equivalentes en `schema-h2.sql`.

## Auditoria

API Experience obtiene el nombre mediante GoREST. API Support persiste ese nombre junto con el proceso de cambio. No se crea una segunda tabla de auditoria porque el enunciado solicita registrar esos valores al registrar el proceso en la base de datos.
