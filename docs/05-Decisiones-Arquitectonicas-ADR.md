# Decisiones arquitectonicas

## ADR-001 - Separacion en API Experience y API Support

**Decision:** La solucion se divide en API Experience y API Support.

**Motivo:** El enunciado solicita dos APIs con responsabilidades distintas: comunicacion con el cliente y operaciones con la base de datos.

**Consecuencia:** API Experience se comunica con GoREST y API Support.

## ADR-002 - Arquitectura por capas

**Decision:** Cada API separa entrada HTTP, logica de aplicacion, dominio e infraestructura.

**Motivo:** Facilitar la separacion de responsabilidades y las pruebas.

## ADR-003 - Spring WebFlux

**Decision:** Se utiliza Spring WebFlux.

**Motivo:** El enunciado exige programacion reactiva.

## ADR-004 - Cliente HTTP reactivo

**Decision:** API Experience utiliza WebClient para consumir GoREST y API Support.

**Motivo:** Mantener las comunicaciones externas dentro del flujo reactivo.

## ADR-005 - JWT

**Decision:** La autenticacion de API Experience utiliza JWT.

**Motivo:** Es un requerimiento explicito del desafio.

## ADR-006 - H2

**Decision:** API Support utiliza H2 mediante el perfil `h2` para pruebas.

**Motivo:** H2 es la base indicada para persistencia de datos de prueba.

## ADR-007 - Compatibilidad H2 y MySQL

**Decision:** API Support utiliza R2DBC y dispone de configuracion MySQL por defecto y perfil `h2`.

**Motivo:** La ejecucion se conecta con MySQL y se conserva H2 para pruebas.

## ADR-008 - Calculo del monto final

**Decision:** El monto final se calcula multiplicando el monto inicial por el tipo de cambio.

**Motivo:** Es la formula utilizada para obtener el monto convertido. El enunciado no especifica otra formula.

## ADR-009 - Credenciales de autenticacion

**Decision:** Las credenciales del login y el secreto JWT se configuran mediante variables de entorno.

**Motivo:** El enunciado exige autenticacion JWT, pero no define una tabla ni un servicio para autenticar las credenciales.
