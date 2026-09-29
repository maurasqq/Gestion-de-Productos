SELECT installed_rank, version, description, success FROM flyway_schema_history ORDER BY installed_rank;
SELECT * FROM categoria ORDER BY id;
SELECT * FROM proveedor ORDER BY id;
SELECT p.id, p.codigo, p.nombre, p.precio_venta, p.existencia, p.categoria_id, c.nombre AS categoria, p.proveedor_id, pr.nombre AS proveedor FROM producto p JOIN categoria c ON c.id=p.categoria_id LEFT JOIN proveedor pr ON pr.id=p.proveedor_id ORDER BY p.id;
