# Contrato de APIs

Las APIs utilizan JSON. API Experience protege las operaciones de negocio mediante JWT.

## API Experience

### POST `/api/v1/login`

Autentica al consumidor y genera un JWT.

Solicitud:

```json
{
  "username": "challenge",
  "password": "valor-configurado-en-APP_AUTH_PASSWORD"
}
```

Respuesta:

```json
{
  "token": "<jwt>"
}
```

### POST `/api/v1/exchange`

Realiza una operacion de tipo de cambio.

Header:

```text
Authorization: Bearer <jwt>
```

Solicitud:

```json
{
  "userId": 1,
  "amount": 100.00,
  "sourceCurrency": "USD",
  "targetCurrency": "PEN",
  "exchangeRate": 3.75
}
```

Flujo:

1. Validar el JWT.
2. Consultar GoREST usando `userId`.
3. Obtener el nombre del usuario.
4. Calcular el monto final como monto inicial por tipo de cambio.
5. Enviar los datos a API Support para su registro.
6. Retornar el resultado al cliente.

## API Support

### POST `/api/v1/exchanges`

Registra el proceso de cambio y los datos de auditoria funcional.

### GET `/api/v1/exchanges/{id}`

Busca un registro por su identificador.

### GET `/api/v1/exchanges`

Devuelve los registros existentes mediante un `Flux` reactivo.

### PUT `/api/v1/exchanges/{id}`

Actualiza el registro asociado al ID indicado. La solicitud utiliza los campos de `SupportExchangeRequest`.

## Respuestas de error

La implementacion contempla los siguientes errores:

| Situacion | HTTP | Respuesta |
|---|---:|---|
| Datos de entrada invalidos | 400 | `Datos de entrada inválidos` |
| JWT ausente o invalido | 401 | La solicitud se rechaza antes de ejecutar el caso de uso. |
| Usuario no encontrado en GoREST | 404 | `Usuario no encontrado en GoREST` |
| GoREST no disponible | 502 | `No fue posible conectar con GoREST` |
| API Support no disponible | 502 | `No fue posible conectar con API Support` |
| Registro no encontrado | 404 | `Registro no encontrado` |
| Error no controlado | 500 | `Error interno` |

Las respuestas de error no exponen el stack trace ni detalles internos al cliente.
