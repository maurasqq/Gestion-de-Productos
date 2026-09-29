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
