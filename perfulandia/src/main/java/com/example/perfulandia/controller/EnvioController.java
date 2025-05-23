package com.example.perfulandia.controller;

import com.example.perfulandia.model.Envio;
import com.example.perfulandia.service.EnvioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnvioService envioService;

 
    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping
    public ResponseEntity<List<Envio>> getAllEnvios() {
        List<Envio> lista = envioService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> getEnvioById(@PathVariable Long id) {
        Optional<Envio> envio = envioService.obtenerPorId(id);
        return envio.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Envio> crearEnvio(@RequestBody Envio envio) {
        Envio nuevo = envioService.guardar(envio);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable Long id, @RequestBody Envio envio) {
        try {
            Envio actualizado = envioService.actualizar(id, envio);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEnvio(@PathVariable Long id) {
        if (envioService.obtenerPorId(id).isPresent()) {
            envioService.eliminar(id);
            return ResponseEntity.ok("Envío eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}