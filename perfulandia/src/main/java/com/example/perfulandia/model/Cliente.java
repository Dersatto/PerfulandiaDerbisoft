package com.example.perfulandia.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String email;
    private String direccion;
    private String telefono;



    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
}