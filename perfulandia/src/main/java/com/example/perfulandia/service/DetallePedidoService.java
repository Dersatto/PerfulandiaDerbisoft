package com.example.perfulandia.service;

import com.example.perfulandia.model.DetallePedido;
import com.example.perfulandia.repository.DetallePedidoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    
    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    public List<DetallePedido> obtenerTodos() {
        return detallePedidoRepository.findAll();
    }

    public Optional<DetallePedido> obtenerPorId(Long id) {
        return detallePedidoRepository.findById(id);
    }

    public DetallePedido guardar(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    public ResponseEntity<String> eliminar(Long id) {
        if (detallePedidoRepository.existsById(id)) {
            detallePedidoRepository.deleteById(id);
            return ResponseEntity.ok("Detalle del pedido eliminado exitosamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public DetallePedido actualizar(Long id, DetallePedido nuevoDetalle) {
        return detallePedidoRepository.findById(id)
                .map(detalle -> {
                    detalle.setCantidad(nuevoDetalle.getCantidad());
                    detalle.setPrecioUnitario(nuevoDetalle.getPrecioUnitario());
                    detalle.setProducto(nuevoDetalle.getProducto());
                    detalle.setPedido(nuevoDetalle.getPedido());
                    return detallePedidoRepository.save(detalle);
                })
                .orElseThrow(() -> new RuntimeException("DetallePedido no encontrado con id: " + id));
    }
}