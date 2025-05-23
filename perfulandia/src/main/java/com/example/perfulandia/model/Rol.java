package com.example.perfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

public String getDescripcion() {
    return descripcion;
}

public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;  // Ej: ADMIN, VENDEDOR, CLIENTE

}