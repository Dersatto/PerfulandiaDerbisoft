package com.example.perfulandia.controller;

import com.example.perfulandia.model.Producto;
import com.example.perfulandia.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listar() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Producto> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }
}