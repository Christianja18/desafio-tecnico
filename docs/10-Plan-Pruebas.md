# Plan de pruebas

## Pruebas unitarias implementadas

- `ExchangeServiceTest`: validacion del usuario, calculo del monto y envio a Support.
- `ExchangeOperationServiceTest`: registro y actualizacion de un registro inexistente.
- `JwtServiceTest`: generacion y lectura de JWT.

La ejecucion actual de Maven finaliza correctamente con 6 pruebas, sin fallos.

## Pruebas de integracion planificadas

- API Experience con GoREST.
- API Experience con API Support.
- API Support con H2.
- Flujo completo de una operacion.

Estas pruebas estan definidas como plan, pero no forman parte de la suite automatizada actual.

## Pruebas funcionales

Se pueden ejecutar mediante Postman o SoapUI:

- Autenticarse y obtener un JWT.
- Realizar una operacion con un usuario existente.
- Rechazar un usuario que no exista en GoREST.
- Registrar una operacion.
- Buscar una operacion registrada.
- Actualizar el tipo de cambio.
- Verificar los datos persistidos.

## Herramientas

- JUnit 5.
- Mockito.
- Reactor Test.
- Postman o SoapUI.
