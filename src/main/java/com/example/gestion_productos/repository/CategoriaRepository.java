package com.example.gestion_productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_productos.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
