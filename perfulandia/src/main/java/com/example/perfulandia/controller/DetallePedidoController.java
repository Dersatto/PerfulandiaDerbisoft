package com.example.perfulandia.controller;

import com.example.perfulandia.model.DetallePedido;
import com.example.perfulandia.service.DetallePedidoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detallepedidos")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

   
    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public ResponseEntity<List<DetallePedido>> getAllDetalles() {
        List<DetallePedido> lista = detallePedidoService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> getDetalleById(@PathVariable Long id) {
        Optional<DetallePedido> detalle = detallePedidoService.obtenerPorId(id);
        return detalle.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetallePedido> crearDetalle(@RequestBody DetallePedido detalle) {
        DetallePedido nuevo = detallePedidoService.guardar(detalle);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizarDetalle(@PathVariable Long id, @RequestBody DetallePedido detalle) {
        try {
            DetallePedido actualizado = detallePedidoService.actualizar(id, detalle);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDetalle(@PathVariable Long id) {
        if (detallePedidoService.obtenerPorId(id).isPresent()) {
            detallePedidoService.eliminar(id);
            return ResponseEntity.ok("Detalle de pedido eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}