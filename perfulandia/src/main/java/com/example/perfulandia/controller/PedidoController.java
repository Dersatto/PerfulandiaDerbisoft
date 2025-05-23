package com.example.perfulandia.controller;

import com.example.perfulandia.model.Pedido;
import com.example.perfulandia.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> obtenerPorId(@PathVariable Long id) {
        return pedidoService.obtenerPorId(id);
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        return pedidoService.guardar(pedido);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
    }
}