package com.example.perfulandia.service;

import com.example.perfulandia.model.Pago;
import com.example.perfulandia.repository.PagoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> obtenerPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago guardar(Pago pago) {
        return pagoRepository.save(pago);
    }

    public ResponseEntity<String> eliminar(Long id) {
        if (pagoRepository.existsById(id)) {
            pagoRepository.deleteById(id);
            return ResponseEntity.ok("Pago eliminado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Pago actualizar(Long id, Pago nuevoPago) {
        return pagoRepository.findById(id)
                .map(pago -> {
                    pago.setMetodo(nuevoPago.getMetodo());  // <- CORREGIDO aquí
                    pago.setMonto(nuevoPago.getMonto());
                    pago.setFechaPago(nuevoPago.getFechaPago());
                    pago.setPedido(nuevoPago.getPedido());
                    return pagoRepository.save(pago);
                })
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id));
    }
}