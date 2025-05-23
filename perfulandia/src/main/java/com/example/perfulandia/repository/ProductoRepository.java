package com.example.perfulandia.repository;

import com.example.perfulandia.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

// Esta es la forma correcta
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}