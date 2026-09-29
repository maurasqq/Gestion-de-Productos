package com.example.gestion_productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_productos.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
}
