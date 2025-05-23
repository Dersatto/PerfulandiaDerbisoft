package com.example.perfulandia.controller;

import com.example.perfulandia.model.Pago;
import com.example.perfulandia.service.PagoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

   
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<Pago>> getAllPagos() {
        List<Pago> lista = pagoService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> getPagoById(@PathVariable Long id) {
        Optional<Pago> pago = pagoService.obtenerPorId(id);
        return pago.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pago> crearPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoService.guardar(pago);
        return ResponseEntity.status(201).body(nuevoPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizarPago(@PathVariable Long id, @RequestBody Pago pago) {
        try {
            Pago actualizado = pagoService.actualizar(id, pago);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPago(@PathVariable Long id) {
        if (pagoService.obtenerPorId(id).isPresent()) {
            pagoService.eliminar(id);
            return ResponseEntity.ok("Pago eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}