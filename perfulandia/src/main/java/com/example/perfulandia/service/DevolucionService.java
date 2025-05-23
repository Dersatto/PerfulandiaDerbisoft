package com.example.perfulandia.service;

import com.example.perfulandia.model.Devolucion;
import com.example.perfulandia.repository.DevolucionRepository;

import jakarta.persistence.Id;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevolucionService {

    private final DevolucionRepository devolucionRepository;

    
    public DevolucionService(DevolucionRepository devolucionRepository) {
        this.devolucionRepository = devolucionRepository;
    }

    public List<Devolucion> obtenerTodos() {
        return devolucionRepository.findAll();
    }
  @Id
    public Optional<Devolucion> obtenerPorId(Long id) {
        return devolucionRepository.findById(id);
    }

    public Devolucion guardar(Devolucion devolucion) {
        return devolucionRepository.save(devolucion);
    }

    public Devolucion actualizar(Long id, Devolucion devolucion) {
        return devolucionRepository.findById(id)
                .map(existing -> {
                    devolucion.setId(id); // asegúrate que el ID se mantenga
                    return devolucionRepository.save(devolucion);
                })
                .orElseThrow(() -> new RuntimeException("Devolución no encontrada con ID: " + id));
    }

    public void eliminar(Long id) {
        devolucionRepository.deleteById(id);
    }
}