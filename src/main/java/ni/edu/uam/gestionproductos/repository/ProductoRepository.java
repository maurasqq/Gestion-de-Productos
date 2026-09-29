package ni.edu.uam.gestionproductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ni.edu.uam.gestionproductos.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
