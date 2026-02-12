package com.example.autoflex.controller;

import com.example.autoflex.dto.ProductionSuggestionDTO;
import com.example.autoflex.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ProductionController {

    @Autowired
    private ProductionService productionService;

    @GetMapping("/suggested-production")
    public List<ProductionSuggestionDTO> getSuggestions() {
        return productionService.calculateSuggestions();
    }
}