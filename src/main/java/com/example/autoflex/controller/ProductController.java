package com.example.autoflex.controller;

import com.example.autoflex.model.Product;
import com.example.autoflex.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        if (product.getCompositions() != null) {
            product.getCompositions().forEach(comp -> comp.setProduct(product));
        }
        return productRepository.save(product);
    }

    @DeleteMapping("/{name}")
    @Transactional
    public ResponseEntity<Void> deleteProduct(@PathVariable String name) {
        productRepository.deleteByName(name);
        return ResponseEntity.noContent().build();
    }
}