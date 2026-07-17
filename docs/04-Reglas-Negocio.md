# Reglas de negocio

Estas reglas se derivan del enunciado y de las decisiones documentadas para poder implementar el flujo.

- RN01: La operacion recibe el ID del usuario.
- RN02: La operacion recibe un monto.
- RN03: La operacion indica moneda de origen y moneda de destino.
- RN04: La operacion utiliza un tipo de cambio.
- RN05: Antes de procesar la operacion, API Experience valida mediante GoREST que el usuario exista.
- RN06: Si el usuario existe, se obtiene su nombre para el registro posterior.
- RN07: API Experience envia a API Support el nombre obtenido desde GoREST.
- RN08: API Support registra quien hizo la solicitud por cada operacion realizada.
- RN09: El registro persistido contiene nombre de usuario, monto inicial, monto final, tipo de cambio y fecha del proceso.
- RN10: API Support permite registrar, actualizar y buscar el tipo de cambio.

## Decisiones de implementacion

- El monto final se calcula como `monto inicial x tipo de cambio`.
- Se validan los campos requeridos, pero no se agregan reglas sobre monto minimo, monedas permitidas o monedas diferentes porque no fueron indicadas en el enunciado.
