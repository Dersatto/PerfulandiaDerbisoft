package com.example.perfulandia.controller;

import com.example.perfulandia.model.Sucursal;
import com.example.perfulandia.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sucursales")
@CrossOrigin(origins = "*")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<Sucursal> listar() {
        return sucursalService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Optional<Sucursal> obtenerPorId(@PathVariable Long id) {
        return sucursalService.obtenerPorId(id);
    }

    @PostMapping
    public Sucursal crear(@RequestBody Sucursal sucursal) {
        return sucursalService.guardar(sucursal);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        sucursalService.eliminar(id);
    }
}