# Gestión de productos

Esta práctica implementa una API REST sencilla para administrar categorías, productos y proveedores. La información se guarda en PostgreSQL y la estructura de las tablas se controla con Flyway.

## Preguntas

### 1. ¿Cuál es la función de Spring Data JPA?

Spring Data JPA facilita el acceso a la base de datos. Permite crear repositorios y realizar operaciones comunes como guardar, listar, buscar y eliminar sin escribir toda la implementación manualmente.

### 2. ¿Qué función cumple Hibernate?

Hibernate convierte los objetos Java en registros de la base de datos y los registros en objetos. También genera y ejecuta el SQL necesario para trabajar con las entidades.

### 3. ¿Qué diferencia existe entre JPA y Hibernate?

JPA es una especificación que define cómo manejar persistencia en Java. Hibernate es una implementación de esa especificación que realiza el trabajo concreto.

### 4. ¿Qué función cumple `@Entity`?

`@Entity` indica que una clase Java representa una entidad persistente y que sus objetos se pueden guardar en una tabla de la base de datos.

### 5. ¿Qué función cumple `@ManyToOne`?

`@ManyToOne` representa una relación en la que muchos registros de una entidad pueden estar asociados con un solo registro de otra. En esta práctica, varios productos pueden tener la misma categoría o el mismo proveedor.

### 6. ¿Qué función cumple `@JoinColumn`?

`@JoinColumn` indica cuál columna contiene la clave foránea de una relación. Aquí se utiliza con `categoria_id` y `proveedor_id` en la tabla `producto`.

### 7. ¿Qué función cumple `JpaRepository`?

`JpaRepository` proporciona métodos ya implementados como `findAll`, `findById`, `save`, `deleteById`, `existsById` y `count`.

### 8. ¿Por qué se utilizan migraciones?

Las migraciones permiten crear y modificar la estructura de la base de datos de forma ordenada, repetible y con un historial de cambios. Así todos pueden tener el mismo esquema.

### 9. ¿Qué diferencia existe entre V1, V2 y V3?

V1 crea las tablas iniciales `categoria` y `producto`. V2 agrega la descripción del producto. V3 crea `proveedor` y agrega la relación entre proveedor y producto.

### 10. ¿Qué problema puede producir una relación bidireccional al generar JSON?

Puede provocar referencias circulares: la categoría o el proveedor incluye productos y cada producto vuelve a incluir la categoría o el proveedor. Esto puede generar recursión infinita, respuestas demasiado grandes o errores de serialización.

## Estructura general del proyecto

```text
src/main/java/ni/edu/uam/gestionproductos/
├── controller/   CategoriaController, ProductoController, ProveedorController
├── entity/       Categoria, Producto, Proveedor
└── repository/   CategoriaRepository, ProductoRepository, ProveedorRepository

src/main/resources/
├── application.properties
└── db/migration/ V1__crear_tablas.sql, V2__agregar_descripcion_producto.sql,
                  V3__crear_proveedor_y_relacionar_producto.sql
```

`spring.jpa.hibernate.ddl-auto=validate` hace que Hibernate compare el esquema
existente con las entidades al iniciar. Si encuentra diferencias, la aplicación
falla; no crea, elimina ni modifica tablas. La estructura la administra Flyway.

## Conclusión

La práctica permitió conectar una aplicación Spring Boot con PostgreSQL.
Spring Data JPA simplificó las operaciones de persistencia mediante repositorios.
Hibernate se encargó de relacionar las entidades Java con las tablas.
Flyway mantuvo los cambios de la base de datos separados y ordenados.
Las relaciones `ManyToOne` permitieron asociar cada producto con su categoría y proveedor.
Finalmente, los controladores expusieron operaciones sencillas para guardar y consultar la información.

## Capturas para el entregable

Tomar manualmente las siguientes capturas:

- La configuración de conexión en `application.properties`.
- La estructura de paquetes `controller`, `entity` y `repository`.
- Las entidades `Categoria`, `Producto` y `Proveedor`.
- Las migraciones V1, V2 y V3.
- Las solicitudes y respuestas realizadas en Postman.
- Las tablas `categoria`, `producto` y `proveedor` en PostgreSQL.
- El contenido de la tabla `flyway_schema_history`.

## Configuración y ejecución

El proyecto usa Maven, Java 21, groupId `ni.edu.uam` y paquete raíz
`ni.edu.uam.gestionproductos`. Las dependencias necesarias ya estaban presentes.
No utiliza Lombok.

La base `gestion_productos` debe existir y la contraseña local del usuario
`postgres` debe coincidir con la configurada en `application.properties`.
Si la contraseña local es diferente, actualiza ese valor únicamente en tu copia
local antes de iniciar la aplicación. Luego, desde la raíz del proyecto:

```powershell
.\mvnw.cmd verify
.\mvnw.cmd spring-boot:run
```

Flyway aplica las migraciones y Hibernate valida el esquema con
`spring.jpa.hibernate.ddl-auto=validate`.

## Diagrama de relaciones

```text
CATEGORIA                        PRODUCTO                         PROVEEDOR
id (PK)                1       N id (PK)                 N       1 id (PK)
nombre                  ──────── categoria_id (FK)                nombre
activa                           proveedor_id (FK) ────────────── telefono
                                 codigo (UNIQUE)                  correo
                                 nombre                           activo
                                 precio_venta
                                 existencia
                                 descripcion
```

Cada producto requiere una categoría. Su proveedor es opcional para conservar
el POST inicial de la práctica y permitir productos anteriores a V3.
Ambas asociaciones Java son unidireccionales desde Producto; no hay listas
inversas que produzcan ciclos al serializar JSON.

## Verificación real del 28 de septiembre de 2026

- `mvnw verify`: BUILD SUCCESS, una prueba ejecutada, cero errores y cero omisiones.
- Aplicación iniciada en `http://localhost:8080`.
- Los GET de categorías, proveedores y productos respondieron HTTP 200.
- Se crearon mediante POST tres categorías, dos proveedores y dos productos.
- LAP-001 verifica el flujo inicial sin proveedor.
- LAP-002 tiene categoría Computadoras y proveedor Tech Distribuciones.
- PostgreSQL confirmó las migraciones 1, 2 y 3 con `success = true`.
- Hibernate validó el esquema durante el arranque.

Evidencias obtenidas directamente durante la ejecución:

- [Respuestas HTTP reales](evidencias/respuestas-api.json).
- [Resultados SQL de tablas, relaciones e historial Flyway](evidencias/postgresql.txt).
- [Consultas reproducibles en pgAdmin](evidencias/consultas.sql).

Estas evidencias son salidas reales de HTTP y PostgreSQL. No son capturas de
Postman. Las capturas de Postman y pgAdmin siguen pendientes: Windows estaba
bloqueado al intentar obtenerlas.

## Verificación de esta revisión

- `mvnw.cmd -DskipTests package`: BUILD SUCCESS.
- `mvnw.cmd test`: no completó porque PostgreSQL rechazó la contraseña de
  `postgres` configurada en el entorno actual (SQL State `28P01`).
- No se ejecutaron nuevas peticiones HTTP ni nuevas consultas PostgreSQL en esta
  revisión por esa misma falta de autenticación.
- La estructura Java, las migraciones, la colección de Postman y las evidencias
  históricas anteriores se conservaron porque ya estaban presentes en el proyecto.

## Verificación actual con PostgreSQL

En la revisión del 5 de octubre de 2026 se corrigió la contraseña local de
PostgreSQL a `666`. La prueba `mvnw.cmd test` terminó con BUILD SUCCESS y una
prueba aprobada. Flyway validó las cuatro migraciones y aplicó V4; Hibernate
validó el esquema correctamente.

También respondieron HTTP 200 `GET /api/categorias`, `GET /api/productos`,
`GET /api/productos/categoria/1` y `GET /api/etiquetas`. Se crearon las etiquetas
`Oficina` y `Portatil`, el producto `LAP-003` y su asociación N:N. Estos datos
quedaron en la base local para facilitar las capturas de la práctica.

## Pruebas en Postman, en orden

Importa [la colección](postman/Gestion-productos.postman_collection.json).
Usa Body → raw → JSON en cada POST. La colección guarda automáticamente los IDs
de Computadoras y Tech Distribuciones como variables para crear productos.

Los registros de ejemplo ya fueron insertados durante la verificación.
Puedes ejecutar los GET inmediatamente. Para repetir los POST de productos,
cambia sus códigos por valores nuevos (por ejemplo LAP-003 y LAP-004), porque
`codigo` es único. Repetir los POST de categorías o proveedores crea registros
adicionales. No borres datos ni migraciones para repetir la prueba.

Base URL: `http://localhost:8080`. En los JSON siguientes, reemplaza los IDs por
los devueltos por tus POST si difieren de 1.

1. `GET /api/categorias`

2. `POST /api/categorias`

```json
{
  "nombre": "Computadoras",
  "activa": true
}
```

3. `POST /api/categorias`

```json
{
  "nombre": "Accesorios",
  "activa": true
}
```

4. `POST /api/categorias`

```json
{
  "nombre": "Monitores",
  "activa": true
}
```

5. `GET /api/categorias`

6. `POST /api/proveedores`

```json
{
  "nombre": "Tech Distribuciones",
  "telefono": "8888-1111",
  "correo": "ventas@techdist.com",
  "activo": true
}
```

7. `POST /api/proveedores`

```json
{
  "nombre": "Global Hardware",
  "telefono": "8888-2222",
  "correo": "ventas@globalhardware.com",
  "activo": true
}
```

8. `GET /api/proveedores`

9. `POST /api/productos`

```json
{
  "codigo": "LAP-001",
  "nombre": "Laptop Lenovo",
  "categoria": {
    "id": 1
  },
  "precioVenta": 850,
  "existencia": 10
}
```

10. `POST /api/productos`

```json
{
  "codigo": "LAP-002",
  "nombre": "Laptop Dell",
  "descripcion": "Laptop empresarial",
  "categoria": {
    "id": 1
  },
  "proveedor": {
    "id": 1
  },
  "precioVenta": 950,
  "existencia": 8
}
```

11. `GET /api/productos`


Para las capturas pendientes, muestra la URL, método, cuerpo enviado y respuesta
con estado HTTP en Postman. En pgAdmin ejecuta `evidencias/consultas.sql` y captura
los resultados de cada consulta, incluida `flyway_schema_history`.

---

# Práctica Guiada 2: servicios, DTOs y etiquetas

## Responsabilidades de los paquetes

- `controller`: recibe solicitudes HTTP y devuelve respuestas.
- `service`: contiene la lógica de negocio y coordina las operaciones.
- `repository`: accede a las entidades mediante Spring Data JPA.
- `entity`: representa las tablas y relaciones persistentes.
- `dto`: define los datos que recibe la API sin exponer la entidad como entrada.

## Cambios implementados

`ProductoController` ahora delega en `ProductoService`. `ProductoService` registra,
consulta, actualiza, elimina y busca productos por categoría. El DTO recibe
`categoriaId`, `proveedorId` y una lista de `etiquetaIds`; el servicio convierte
esos identificadores en relaciones JPA.

La relación categoría-producto es bidireccional: una categoría tiene muchos
productos y cada producto pertenece a una categoría. La clave foránea
`categoria_id` permanece en `producto`. Se usa `@JsonIgnore` en la colección
inversa para evitar ciclos al generar JSON.

## Relación Muchos a Muchos

```mermaid
erDiagram
    CATEGORIA ||--o{ PRODUCTO : contiene
    PROVEEDOR ||--o{ PRODUCTO : suministra
    PRODUCTO }o--o{ ETIQUETA : clasifica
    PRODUCTO ||--o{ PRODUCTO_ETIQUETA : tiene
    ETIQUETA ||--o{ PRODUCTO_ETIQUETA : pertenece
```

`@ManyToMany` representa que un producto puede tener varias etiquetas y una
etiqueta puede estar asociada con varios productos. `@JoinTable` define la tabla
intermedia `producto_etiqueta`, que almacena las dos claves foráneas.

La migración `V4__crear_etiquetas.sql` crea `etiqueta` y `producto_etiqueta`.
Las migraciones anteriores V1, V2 y V3 no se modificaron.

## Endpoints agregados y CRUD de productos

- `GET /api/productos`
- `GET /api/productos/{id}`
- `GET /api/productos/categoria/{categoriaId}`
- `POST /api/productos`
- `PUT /api/productos/{id}`
- `DELETE /api/productos/{id}`
- `GET /api/etiquetas`
- `POST /api/etiquetas`
- `PUT /api/productos/{id}/etiquetas`

Ejemplo de `POST` o `PUT` de producto:

```json
{
  "codigo": "LAP-003",
  "nombre": "Laptop HP",
  "descripcion": "Laptop para oficina",
  "precioVenta": 780.00,
  "existencia": 6,
  "categoriaId": 1,
  "proveedorId": 1,
  "etiquetaIds": [1, 2]
}
```

Para asociar etiquetas a un producto existente, enviar a
`PUT /api/productos/1/etiquetas` un arreglo como `[1, 2]`.

## Comprobación de aprendizaje

1. Una clase Service concentra la lógica de negocio y coordina repositorios.
2. El controlador no debería contener toda la lógica porque debe encargarse de HTTP, no de persistencia y reglas del dominio.
3. Un DTO es un objeto para transportar datos entre el cliente y la API.
4. Una entidad JPA representa una tabla persistente; un DTO representa los datos de una operación concreta.
5. Recibir `categoriaId` evita enviar una entidad completa y permite que el Service valide y cargue la relación.
6. `@OneToMany` representa uno a muchos y `@ManyToOne` muchos a uno.
7. La clave foránea se almacena en `producto.categoria_id`.
8. `@ManyToMany` representa una relación muchos a muchos entre dos entidades.
9. `@JoinTable` configura la tabla que mantiene las claves de una relación N:N.
10. `producto_etiqueta` es necesaria porque una relación N:N no se guarda directamente en una sola tabla.
11. El Repository realiza operaciones de acceso a datos y consultas mediante JPA.
12. El flujo es: Cliente → Controller → Service → Repository → PostgreSQL; la respuesta regresa por la misma cadena en sentido inverso.

## Capturas adicionales

Además de las capturas anteriores, tomar capturas de los paquetes `service` y
`dto`, `ProductoService`, `ProductoRequestDTO`, los métodos GET/POST/PUT/DELETE,
la consulta por categoría, la migración V4, la tabla `etiqueta`, la tabla
`producto_etiqueta` y la asociación enviada desde Postman.
