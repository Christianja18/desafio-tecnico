# Ejecucion

## Requisitos

- Java 17.
- Maven.
- MySQL para la configuracion de ejecucion de API Support, o H2 mediante el perfil `h2`.

## Pruebas

Desde la carpeta `desafio-tecnico`:

```bash
./mvnw test
```

## API Support con H2

```bash
./mvnw -pl support-api spring-boot:run -Dspring-boot.run.profiles=h2
```

En IntelliJ IDEA, ejecutar primero `Support API - H2` y luego `Experience API - Local`.

## API Support con MySQL

Configurar las variables:

```text
R2DBC_URL=r2dbc:mysql://localhost:3306/exchange_db
R2DBC_USERNAME=root
R2DBC_PASSWORD=********
```

## API Experience

Configurar:

```text
APP_AUTH_PASSWORD=********
APP_JWT_SECRET=********
```

El secreto JWT debe tener al menos 32 bytes.

Puertos predeterminados:

- API Experience: `8080`.
- API Support: `8081`.

## Swagger

```text
http://localhost:8080/swagger-ui.html
http://localhost:8081/swagger-ui.html
```

## Cliente de pruebas

Las APIs pueden consumirse desde Postman o SoapUI utilizando los endpoints definidos en el contrato de APIs.
