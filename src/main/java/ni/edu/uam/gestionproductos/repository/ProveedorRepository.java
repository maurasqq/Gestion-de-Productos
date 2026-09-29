package ni.edu.uam.gestionproductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ni.edu.uam.gestionproductos.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
}
