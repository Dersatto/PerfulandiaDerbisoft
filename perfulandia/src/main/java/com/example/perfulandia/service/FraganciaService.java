package com.example.perfulandia.service;

import com.example.perfulandia.model.Fragancia;
import com.example.perfulandia.repository.FraganciaRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FraganciaService {

    private final FraganciaRepository fraganciaRepository;


    public FraganciaService(FraganciaRepository fraganciaRepository) {
        this.fraganciaRepository = fraganciaRepository;
    }

    public List<Fragancia> obtenerTodos() {
    return fraganciaRepository.findAll();
   }

    public Optional<Fragancia> obtenerPorId(Long id) {
        return fraganciaRepository.findById(id);
    }

    public Fragancia guardar(Fragancia fragancia) {
        return fraganciaRepository.save(fragancia);
    }

    public ResponseEntity<String> eliminar(Long id) {
        if (fraganciaRepository.existsById(id)) {
            fraganciaRepository.deleteById(id);
            return ResponseEntity.ok("Fragancia eliminada exitosamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Fragancia actualizar(Long id, Fragancia nuevaFragancia) {
        return fraganciaRepository.findById(id)
                .map(fragancia -> {
                    fragancia.setNombre(nuevaFragancia.getNombre());
                    fragancia.setDescripcion(nuevaFragancia.getDescripcion());
                    // agrega aquí más campos si se añaden después
                    return fraganciaRepository.save(fragancia);
                })
                .orElseThrow(() -> new RuntimeException("Fragancia no encontrada con id: " + id));
    }
}