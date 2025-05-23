package com.example.perfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String descripcion;
    private int precio;
    private int stock;
    private String categoria;

    @ManyToOne
@JoinColumn(name = "fragancia_id")
private Fragancia fragancia;
}