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

## Entrega en UAM Virtual

Entregue los siguientes elementos:

1. Enlace al repositorio GitHub, indicando la rama `seguridad-jwt`.
2. La colección exportada `postman/NicaMarket-JWT.postman_collection.json`.
3. Cinco capturas de resultados en Postman, mostrando por ejemplo: saludo público `200`, login exitoso `200`, recurso sin token `401`, consulta de categorías con operador `200` y escritura de categoría con operador `403`.
4. Este README con las instrucciones breves de configuración y ejecución.

No publique el valor de `JWT_SECRET_BASE64`, tokens vigentes ni capturas que los expongan.

## Preguntas de cierre

1. **¿En qué se diferencian HTTP 401 y 403?**

   `401 Unauthorized` indica que no se presentó una autenticación válida, por ejemplo cuando falta el token o está vencido. `403 Forbidden` indica que el usuario sí está autenticado, pero no tiene el rol necesario para realizar la operación.

2. **¿Por qué no basta con leer el contenido de un JWT para confiar en él?**

   El contenido de un JWT está codificado, no cifrado, y puede ser leído por cualquiera. La confianza depende de validar su firma, vencimiento y demás reclamaciones con una clave conocida por el servidor.

3. **¿Qué función cumple `JwtDecoder`?**

   `JwtDecoder` valida y decodifica el token recibido. Comprueba la firma HS256 y las reclamaciones como vencimiento antes de permitir el acceso a un endpoint protegido.

4. **¿Qué riesgos ocasionaría publicar la clave utilizada para firmar tokens?**

   Cualquier persona podría crear tokens firmados como `ADMIN`, hacerse pasar por otros usuarios y acceder o modificar recursos protegidos. Por eso la clave debe mantenerse fuera del repositorio y de las capturas.
