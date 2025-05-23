package com.example.perfulandia.controller;

import com.example.perfulandia.model.Factura;
import com.example.perfulandia.service.FacturaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService facturaService;


    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> lista = facturaService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long id) {
        Optional<Factura> factura = facturaService.obtenerPorId(id);
        return factura.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) {
        Factura nuevaFactura = facturaService.guardar(factura);
        return ResponseEntity.status(201).body(nuevaFactura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> actualizarFactura(@PathVariable Long id, @RequestBody Factura factura) {
        try {
            Factura actualizado = facturaService.actualizar(id, factura);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarFactura(@PathVariable Long id) {
        if (facturaService.obtenerPorId(id).isPresent()) {
            facturaService.eliminar(id);
            return ResponseEntity.ok("Factura eliminada correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}