package com.example.gestion_productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_productos.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
