package com.example.perfulandia.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;

    private LocalDate fechaEnvio;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    // Getters y setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public LocalDate getFechaEnvio() { return fechaEnvio; }

    public void setFechaEnvio(LocalDate fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public Pedido getPedido() { return pedido; }

    public void setPedido(Pedido pedido) { this.pedido = pedido; }
}