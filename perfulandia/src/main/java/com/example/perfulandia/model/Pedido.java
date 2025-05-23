package com.example.perfulandia.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "pedido_producto", 
        joinColumns = @JoinColumn(name = "pedido_id"), 
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;

    private double total;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = true)
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuario;

    // Nueva relación con pagos (bidireccional)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
   

    public void calcularTotal() {
        this.total = productos.stream()
                               .mapToDouble(Producto::getPrecio)
                               .sum();
    }

    public enum EstadoPedido {
        EN_PROCESO, ENVIADO, ENTREGADO, CANCELADO
    }
}