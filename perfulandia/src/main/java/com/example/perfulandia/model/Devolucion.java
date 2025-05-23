package com.example.perfulandia.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "devolucion")
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public void setId(Long id) {
    this.id = id;
}
    // Fecha en que se realiza la devolución
    @Column(nullable = false) 
    private LocalDate fechaDevolucion;

    // Motivo de la devolución
    @Column(length = 255)
    private String motivo;

    // Estado de la devolución (Ej: Pendiente, Aprobada, Rechazada)
    @Column(length = 50)
    private String estado;

    // Relación con el pedido que se está devolviendo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    // Constructor sin argumentos requerido por JPA
    public Devolucion() {}

    // Getters y setters
    // ...

}