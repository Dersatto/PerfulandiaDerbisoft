package com.example.perfulandia.controller;

import com.example.perfulandia.model.Fragancia;
import com.example.perfulandia.service.FraganciaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fragancias")
public class FraganciaController {

    private final FraganciaService fraganciaService;


    public FraganciaController(FraganciaService fraganciaService) {
        this.fraganciaService = fraganciaService;
    }

    @GetMapping
    public ResponseEntity<List<Fragancia>> getAllFragancias() {
        List<Fragancia> lista = fraganciaService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fragancia> getFraganciaById(@PathVariable Long id) {
        Optional<Fragancia> fragancia = fraganciaService.obtenerPorId(id);
        return fragancia.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Fragancia> crearFragancia(@RequestBody Fragancia fragancia) {
        Fragancia nueva = fraganciaService.guardar(fragancia);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fragancia> actualizarFragancia(@PathVariable Long id, @RequestBody Fragancia fragancia) {
        try {
            Fragancia actualizada = fraganciaService.actualizar(id, fragancia);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarFragancia(@PathVariable Long id) {
        if (fraganciaService.obtenerPorId(id).isPresent()) {
            fraganciaService.eliminar(id);
            return ResponseEntity.ok("Fragancia eliminada correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}