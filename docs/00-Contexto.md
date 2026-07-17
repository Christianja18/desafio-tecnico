# Contexto del proyecto

## Proposito

El desafio consiste en desarrollar dos APIs para realizar operaciones de tipo de cambio.

## Actores y componentes

- Cliente: frontend, Postman u otra herramienta HTTP.
- API Experience: API expuesta al cliente y responsable de orquestar la solicitud.
- GoREST: servicio externo utilizado para validar que el usuario exista y obtener su nombre.
- API Support: API interna responsable de las operaciones relacionadas con la base de datos.
- Base de datos: persistencia de los procesos de tipo de cambio y de los datos de auditoria funcional.

## Flujo general

1. El cliente se autentica en API Experience.
2. El cliente solicita una operacion de cambio indicando usuario, monto, monedas y tipo de cambio.
3. API Experience valida el usuario mediante GoREST.
4. API Experience obtiene el nombre del usuario y envia los datos a API Support.
5. API Support registra la operacion y la auditoria funcional en la base de datos.
6. API Experience devuelve el resultado al cliente.

## Alcance tecnico implementado

- Java 17.
- Spring Boot.
- Spring WebFlux.
- Programacion reactiva con Mono, Flux, WebClient y R2DBC.
- MySQL como configuracion de ejecucion de API Support.
- H2 mediante el perfil `h2` para pruebas.
- JWT.
- Swagger.
- Consumo mediante Postman o SoapUI.

## Decisiones implementadas

- El login recibe `username` y `password`.
- Las credenciales de login y el secreto JWT se configuran mediante variables de entorno.
- El monto final se calcula como monto inicial multiplicado por tipo de cambio.
- API Support busca por ID y tambien expone la consulta general `GET /api/v1/exchanges`.
- Los tipos fisicos de datos estan definidos en los scripts de esquema.
