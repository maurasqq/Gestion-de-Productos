# Gestión de productos

## Descripción

Este proyecto es una API REST para manejar productos categorias proveedores y
etiquetas. Se hizo con Spring Boot y usa PostgreSQL, Spring Data JPA, Hibernate
y Flyway.

## Estructura del proyecto

```text
src/main/java/ni/edu/uam/gestionproductos
├── controller
├── dto
├── entity
├── repository
└── service
```

El controller recibe las peticiones. Los services contienen la logica de la
aplicación. El repository se encarga del acceso a datos. Las entity representan
las tablas y los dto reciben los datos que manda el cliente.

## ProductoService

`ProductoService` tiene los metodos para listar buscar guardar actualizar y
eliminar productos. Tambien registra las relaciones con categoria proveedor y
etiqueta. Tambien existen `CategoriaService`, `ProveedorService` y
`EtiquetaService` para que cada controller use su propio service en vez de
usar directamente el repository.

## DTOs

La API usa DTOs para recibir datos en los POST. Existen `CategoriaRequestDTO`,
`ProductoRequestDTO`, `ProveedorRequestDTO` y `EtiquetaRequestDTO`. Las
respuestas siguen usando las entidades para mostrar los datos guardados.

### ProductoRequestDTO

Este DTO recibe los datos del producto y los ids de las relaciones

```json
{
  "codigo": "TEC-001",
  "nombre": "Teclado mecanico",
  "descripcion": "Teclado para oficina",
  "precioVenta": 75.50,
  "existencia": 20,
  "categoriaId": 1,
  "proveedorId": 1,
  "etiquetaIds": [1, 2]
}
```

Usar `categoriaId` es mas sencillo que enviar toda la categoria. El service
busca la categoria y la asigna al producto.

## Endpoints

### Productos

```text
GET    /api/productos
GET    /api/productos/{id}
POST   /api/productos
PUT    /api/productos/{id}
DELETE /api/productos/{id}
GET    /api/productos/categoria/{categoriaId}
GET    /api/productos/etiqueta/{etiquetaId}
POST   /api/productos/{productoId}/etiquetas/{etiquetaId}
DELETE /api/productos/{productoId}/etiquetas/{etiquetaId}
```

El DELETE de producto devuelve 204. El DELETE de etiqueta tambien devuelve 204
y solo quita la relacion no elimina las entidades.

### Categorias proveedores y etiquetas

```text
GET  /api/categorias
POST /api/categorias
GET  /api/proveedores
POST /api/proveedores
GET  /api/etiquetas
POST /api/etiquetas
```

## Relaciones

```text
CATEGORIA 1 ----- N PRODUCTO N ----- N ETIQUETA
                         |
                         N
                     PROVEEDOR
```

La relacion Categoria Producto usa `@ManyToOne` en Producto y `@OneToMany` en
Categoria. La columna `categoria_id` esta en la tabla producto porque ahi se
guarda la clave foranea.

La relacion Producto Etiqueta usa `@ManyToMany` y `@JoinTable`. La tabla
`producto_etiqueta` guarda `producto_id` y `etiqueta_id`. La lista inversa de
Categoria y Etiqueta se ignora en el JSON para evitar ciclos.

## Migraciones

- V1 crea las tablas categoria y producto
- V2 agrega descripcion a producto
- V3 crea proveedor y lo relaciona con producto
- V4 crea etiqueta y producto_etiqueta
- V5 agrega que el nombre de etiqueta no se repita porque V4 ya estaba aplicada

Las migraciones las ejecuta Flyway y Hibernate usa `ddl-auto=validate`. Esto
significa que Hibernate revisa si las tablas coinciden con las entidades pero no
crea ni cambia tablas.

## Reto final

El primer reto elimina una asociacion sin borrar el producto ni la etiqueta

```text
DELETE /api/productos/{productoId}/etiquetas/{etiquetaId}
```

El segundo reto busca los productos que tienen una etiqueta

```text
GET /api/productos/etiqueta/{etiquetaId}
```

Los dos endpoints estan incluidos en la coleccion de Postman.

## Respuestas de comprobacion

1. Un Service contiene la logica de negocio y coordina los repositories.
2. El controller debe manejar HTTP. Si contiene toda la logica se vuelve dificil de mantener.
3. Un DTO es un objeto para transportar datos entre el cliente y la API.
4. La entity representa una tabla JPA y el DTO representa los datos de una peticion.
5. Recibir `categoriaId` evita mandar un objeto Categoria completo y permite validar la relacion en el service.
6. `@OneToMany` representa uno a muchos y `@ManyToOne` muchos a uno.
7. La clave foranea esta en `producto.categoria_id`.
8. `@ManyToMany` representa muchos productos relacionados con muchas etiquetas.
9. `@JoinTable` indica la tabla intermedia de la relacion.
10. `producto_etiqueta` es necesaria para guardar las dos claves de una relacion N a N.
11. El Repository hace las operaciones de acceso a la base mediante Spring Data JPA.
12. El flujo es Cliente → Controller → Service → Repository → PostgreSQL.

## Pruebas

La coleccion para importar esta en:

`postman/Gestion-productos.postman_collection.json`

El proyecto usa Java 21. La aplicacion fue probada con PostgreSQL local y la
base `gestion_productos`. Maven termino la prueba de contexto correctamente y
Flyway valido las cinco migraciones.

Las capturas de la practica estan en `evidencias/evidencias.docx`.

## Conclusion

Se organizo la API separando controller service repository entity y dto. Se
agrego el CRUD de productos la consulta por categoria y la relacion entre
productos y etiquetas. Tambien se implementaron las migraciones y los retos
finales. PostgreSQL guarda los datos y Flyway controla los cambios de la base.
