package com.example.perfulandia.controller;

import com.example.perfulandia.model.Rol;
import com.example.perfulandia.service.RolService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<Rol>> getAllRoles() {
        List<Rol> lista = rolService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable Long id) {
        Optional<Rol> rol = rolService.obtenerPorId(id);
        return rol.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        Rol nuevoRol = rolService.guardar(rol);
        return ResponseEntity.status(201).body(nuevoRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Long id, @RequestBody Rol rol) {
        try {
            Rol actualizado = rolService.actualizar(id, rol);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable Long id) {
        if (rolService.obtenerPorId(id).isPresent()) {
            rolService.eliminar(id);
            return ResponseEntity.ok("Rol eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}