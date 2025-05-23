package com.example.perfulandia.service;

import com.example.perfulandia.model.Envio;
import com.example.perfulandia.repository.EnvioRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnvioService {

    private final EnvioRepository envioRepository;

    
    public EnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    public List<Envio> obtenerTodos() {
        return envioRepository.findAll();
    }

    public Optional<Envio> obtenerPorId(Long id) {
        return envioRepository.findById(id);
    }

    public Envio guardar(Envio envio) {
        return envioRepository.save(envio);
    }

    public boolean eliminar(Long id) {
        if (envioRepository.existsById(id)) {
            envioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Envio actualizar(Long id, Envio nuevoEnvio) {
        return envioRepository.findById(id)
                .map(envio -> {
                    envio.setDireccion(nuevoEnvio.getDireccion());
                    envio.setFechaEnvio(nuevoEnvio.getFechaEnvio());
                    envio.setPedido(nuevoEnvio.getPedido());
                    envio.setEstado(nuevoEnvio.getEstado());
                    return envioRepository.save(envio);
                })
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con id: " + id));
    }
}