package com.example.perfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fragancia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fragancia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}
    @Column(name = "tipo", nullable = false, unique = true, length = 50)
    private String tipo;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}