# Arquitectura

## Flujo de componentes

```text
Cliente (Frontend / Postman / SoapUI)
                 |
                 v
          API Experience
             |       |
             |       +------> GoREST
             |
             +--------------> API Support ------> MySQL / H2 (perfil h2)
```

## Responsabilidades

### API Experience

- Recibir solicitudes del cliente.
- Autenticar mediante JWT.
- Consumir GoREST para validar el usuario y obtener su nombre.
- Enviar a API Support los datos de la operacion y el nombre obtenido.
- Devolver la respuesta al cliente.

### API Support

- Registrar el tipo de cambio.
- Actualizar el tipo de cambio.
- Buscar el tipo de cambio por ID o mediante la consulta general.
- Registrar la informacion solicitada y quien realizo la solicitud.
- Comunicarse con MySQL o H2 mediante R2DBC.

### GoREST

- Validar la existencia del usuario mediante su ID.
- Proporcionar el nombre del usuario.

## Auditoria funcional

API Experience identifica al usuario mediante GoREST y entrega el nombre a API Support. API Support persiste una unica auditoria junto con el proceso de cambio.

## Capas implementadas

- Entrada: controladores y contratos HTTP.
- Aplicacion: servicios y coordinacion de operaciones.
- Dominio: modelo `ExchangeOperation`.
- Infraestructura: WebClient, R2DBC, seguridad y configuracion.
