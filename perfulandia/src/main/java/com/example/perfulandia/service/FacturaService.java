package com.example.perfulandia.service;

import com.example.perfulandia.model.Factura;
import com.example.perfulandia.repository.FacturaRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FacturaService {

    private final FacturaRepository facturaRepository;

    
    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<Factura> obtenerTodos() {
    return facturaRepository.findAll();
    }

    public Optional<Factura> obtenerPorId(Long id) {
        return facturaRepository.findById(id);
    }

    public Factura guardar(Factura factura) {
        return facturaRepository.save(factura);
    }

    public ResponseEntity<String> eliminar(Long id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
            return ResponseEntity.ok("Factura eliminada correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Factura actualizar(Long id, Factura nuevaFactura) {
        return facturaRepository.findById(id)
                .map(factura -> {
                    factura.setFechaEmision(nuevaFactura.getFechaEmision());
                    factura.setMontoTotal(nuevaFactura.getMontoTotal());
                    factura.setCliente(nuevaFactura.getCliente());
                    return facturaRepository.save(factura);
                })
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));
    }
}