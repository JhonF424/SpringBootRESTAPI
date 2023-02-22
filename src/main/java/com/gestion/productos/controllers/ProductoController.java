package com.gestion.productos.controllers;

import com.gestion.productos.models.Producto;
import com.gestion.productos.repository.ProductoRepository;
import com.gestion.productos.service.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {
    @Autowired
    private ProductoServicio servicio;

    @GetMapping("/productos")
    public List<Producto> productos() {
        return servicio.listProducts();
    }

    @GetMapping("/productos/{id}")
    public Producto obtenerProducto(@PathVariable Integer id) {
        try {
            Producto producto = servicio.getProduct(id);
            return new ResponseEntity<Producto>(producto, HttpStatus.OK).getBody();
        } catch (Exception e) {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND).getBody();
        }
    }

    @PostMapping("/productos/nuevo")
    public void guardarProducto(@RequestBody Producto producto) {
        servicio.saveProduct(producto);
    }

    @PutMapping("/productos/edit/{id}")
    public ResponseEntity actualizarProducto(@RequestBody Producto producto, @PathVariable Integer id) {
        try {
            Producto productoExistente = servicio.getProduct(id);
            productoExistente.setNombre(producto.getNombre());
            productoExistente.setPrecio(producto.getPrecio());
            servicio.saveProduct(productoExistente);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/productos/delete/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        try {
            servicio.deleteProduct(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
