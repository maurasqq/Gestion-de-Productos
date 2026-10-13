# NicaMarket – Seguridad JWT

API REST de productos y categorías con Spring Boot 3.5.6, Java 21, Spring Security 6 y PostgreSQL.

## Configuración

1. Cree una clave aleatoria de 32 bytes y conviértala a Base64.
2. Configure el resultado como variable de entorno `JWT_SECRET_BASE64` en la ejecución de IntelliJ.
3. Configure PostgreSQL según `src/main/resources/application.properties`.
4. Ejecute `./mvnw spring-boot:run`.

Nunca agregue la clave ni tokens al repositorio.

## Usuarios de laboratorio

- `admin` / `Admin123!`: rol ADMIN.
- `operador` / `Operador123!`: rol USER.

## Reglas de acceso

- `GET /api/productos/**` y `GET /api/categorias/**`: USER o ADMIN.
- Operaciones de escritura de productos y categorías: ADMIN.
- `POST /api/auth/login` y `/api/publico/**`: públicos.
- `GET /api/auth/perfil`: requiere Bearer Token válido.

Un token ausente o inválido produce `401`; un usuario autenticado sin el rol requerido produce `403`.

## Postman

Importe `postman/NicaMarket-JWT.postman_collection.json`. La colección incluye login, saludo público, perfil, consulta autenticada y una operación de escritura para demostrar las reglas por rol. Configure la variable `baseUrl` con `http://localhost:8080` y ejecute primero los logins para guardar los tokens.

La rama de entrega es `seguridad-jwt`.
