# Requerimientos

## Requerimientos funcionales

### RF01 - Autenticacion

API Experience cuenta con autenticacion basada en JWT.

La implementacion recibe `username` y `password`. Las credenciales se configuran mediante `APP_AUTH_USERNAME` y `APP_AUTH_PASSWORD`; el secreto y la duracion del JWT se configuran mediante `APP_JWT_SECRET` y `APP_JWT_EXPIRATION`.

### RF02 - Validacion del usuario

Para realizar una operacion, API Experience consume GoREST y valida que el ID del usuario exista. Tambien obtiene el nombre del usuario para su registro posterior.

### RF03 - Realizar operacion de tipo de cambio

API Experience permite solicitar una operacion utilizando:

- ID del usuario.
- Monto.
- Moneda de origen.
- Moneda de destino.
- Tipo de cambio.

La implementacion calcula el monto final multiplicando el monto inicial por el tipo de cambio.

### RF04 - Auditoria funcional

Por cada operacion realizada se registra quien hizo la solicitud.

API Experience obtiene el nombre mediante GoREST y lo envia a API Support. API Support persiste una unica auditoria junto con el proceso de cambio.

Los datos persistidos son:

- Nombre de usuario.
- Monto inicial.
- Monto final.
- Tipo de cambio.
- Fecha del proceso.

### RF05 - Registrar tipo de cambio

API Support registra en la base de datos la informacion del proceso y su auditoria funcional.

### RF06 - Buscar tipo de cambio

API Support busca un registro por ID mediante `GET /api/v1/exchanges/{id}` y obtiene los registros mediante `GET /api/v1/exchanges`.

### RF07 - Actualizar tipo de cambio

API Support actualiza la informacion asociada al ID indicado mediante `PUT /api/v1/exchanges/{id}`.

## Requerimientos no funcionales

- Lenguaje Java.
- Framework Spring Boot.
- Programacion reactiva.
- Persistencia reactiva con R2DBC.
- MySQL como configuracion de ejecucion.
- H2 mediante el perfil `h2` para pruebas.
- Manejo de excepciones.
- Seguridad mediante JWT.
- Consumo mediante Postman o SoapUI.
- Documentacion de arquitectura.
- Documentacion mediante Swagger.
