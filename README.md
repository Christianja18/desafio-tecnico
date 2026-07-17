# Desafío técnico

Solución con dos APIs reactivas:

- `experience-api`: autenticación JWT, validación del usuario mediante GoREST y comunicación con API Support.
- `support-api`: registro, actualización y búsqueda de procesos de tipo de cambio mediante R2DBC.
- `common`: contratos compartidos para evitar duplicación de DTOs.

## Ejecución

Desde este directorio:

```bash
./mvnw test
```

Para MySQL, iniciar `support-api` con estas variables:

```text
R2DBC_URL=r2dbc:mysql://localhost:3306/exchange_db
R2DBC_USERNAME=root
R2DBC_PASSWORD=********
```

Para H2, utilizar el perfil `h2`:

```bash
./mvnw -pl support-api spring-boot:run -Dspring-boot.run.profiles=h2
```

En IntelliJ IDEA se pueden ejecutar las configuraciones `Support API - H2` y `Experience API - Local` en ese orden. La configuracion local de Experience utiliza credenciales de desarrollo incluidas solo para ejecucion local; no deben utilizarse fuera de ese entorno.

No ejecutar la clase `DesafioTecnicoApplication`: pertenece al esqueleto inicial y no inicia las dos APIs actuales. Las clases de entrada son `SupportApiApplication` y `ExperienceApiApplication`.

La API Experience requiere configurar `APP_AUTH_PASSWORD` y `APP_JWT_SECRET`. El secreto JWT debe tener al menos 32 bytes.

Puertos por defecto:

- Experience: `8080`
- Support: `8081`
