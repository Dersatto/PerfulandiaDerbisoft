package com.example.perfulandia.service;

import com.example.perfulandia.model.Rol;
import com.example.perfulandia.repository.RolRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    private final RolRepository rolRepository;

    
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

    public Optional<Rol> obtenerPorId(Long id) {
        return rolRepository.findById(id);
    }

    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }

    public ResponseEntity<String> eliminar(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
            return ResponseEntity.ok("Rol eliminado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Rol actualizar(Long id, Rol nuevoRol) {
        return rolRepository.findById(id)
                .map(rol -> {
                    rol.setNombre(nuevoRol.getNombre());
                    rol.setDescripcion(nuevoRol.getDescripcion());
                    return rolRepository.save(rol);
                })
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
    }
}