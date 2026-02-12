package com.example.autoflex.controller;

import com.example.autoflex.model.RawMaterial;
import com.example.autoflex.repository.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materials")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class RawMaterialController {

    @Autowired
    private RawMaterialRepository repository;

    // CREATE
    @PostMapping
    public RawMaterial create(@RequestBody RawMaterial material) {
        return repository.save(material);
    }

    // READ ALL
    @GetMapping
    public List<RawMaterial> getAll() {
        return repository.findAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<RawMaterial> update(@PathVariable Long id, @RequestBody RawMaterial details) {
        return repository.findById(id).map(material -> {
            material.setName(details.getName());
            material.setStockQuantity(details.getStockQuantity());
            return ResponseEntity.ok(repository.save(material));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}