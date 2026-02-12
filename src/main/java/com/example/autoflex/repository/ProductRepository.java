package com.example.autoflex.repository;

import com.example.autoflex.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteByName(String name);
}
