package com.example.perfulandia.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;

    @OneToMany(mappedBy = "sucursal")
    private List<Pedido> pedidos;

    
}