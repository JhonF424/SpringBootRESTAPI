package com.gestion.productos.service;

import com.gestion.productos.models.Producto;
import com.gestion.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio {
    @Autowired
    private ProductoRepository repository;

    public List<Producto> listProducts() {
        return repository.findAll();
    }

    public void saveProduct(Producto producto) {
        repository.save(producto);
    }

    public Producto getProduct(Integer id) {
        return repository.findById(id).get();
    }

    public void deleteProduct(Integer id) {
        repository.deleteById(id);
    }
}
