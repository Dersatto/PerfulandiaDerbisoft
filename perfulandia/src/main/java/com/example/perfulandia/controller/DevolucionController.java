package com.example.perfulandia.controller;

import com.example.perfulandia.model.Devolucion;
import com.example.perfulandia.service.DevolucionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devoluciones")
public class DevolucionController {

    private final DevolucionService devolucionService;


    public DevolucionController(DevolucionService devolucionService) {
        this.devolucionService = devolucionService;
    }

    @GetMapping
    public ResponseEntity<List<Devolucion>> getAllDevoluciones() {
        List<Devolucion> lista = devolucionService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> getDevolucionById(@PathVariable Long id) {
        Optional<Devolucion> devolucion = devolucionService.obtenerPorId(id);
        return devolucion.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Devolucion> crearDevolucion(@RequestBody Devolucion devolucion) {
        Devolucion nueva = devolucionService.guardar(devolucion);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devolucion> actualizarDevolucion(@PathVariable Long id, @RequestBody Devolucion devolucion) {
        try {
            Devolucion actualizada = devolucionService.actualizar(id, devolucion);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDevolucion(@PathVariable Long id) {
        if (devolucionService.obtenerPorId(id).isPresent()) {
            devolucionService.eliminar(id);
            return ResponseEntity.ok("Devolución eliminada correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}