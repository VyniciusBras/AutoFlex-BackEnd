package com.example.autoflex.service;

import com.example.autoflex.dto.MaterialUsedDTO;
import com.example.autoflex.dto.ProductionSuggestionDTO;
import com.example.autoflex.model.Product;
import com.example.autoflex.model.ProductComposition;
import com.example.autoflex.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductionService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductionSuggestionDTO> calculateSuggestions() {
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(Product::getPrice).reversed());

        List<ProductionSuggestionDTO> suggestions = new ArrayList<>();

        for (Product product : products) {

            double canProduce = Double.MAX_VALUE;

            if (product.getCompositions() == null || product.getCompositions().isEmpty()) {
                continue;
            }

            boolean hasError = false;
            List<MaterialUsedDTO> materialsList = new ArrayList<>();

            for (ProductComposition comp : product.getCompositions()) {
                if (comp.getRawMaterial() == null) {
                    hasError = true;
                    break;
                }

                double available = comp.getRawMaterial().getStockQuantity();
                double required = comp.getQuantityRequired();

                if (required <= 0) {
                    hasError = true;
                    break;
                }

                double possible = available / required;

                canProduce = Math.min(canProduce, possible);
                materialsList.add(new MaterialUsedDTO(
                        comp.getRawMaterial().getName(),
                        required
                ));
            }

            if (hasError) continue;

            int finalQuantity = (int) Math.floor(canProduce);

            if (finalQuantity > 0) {
                suggestions.add(new ProductionSuggestionDTO(
                        product.getName(),
                        finalQuantity,
                        (double) (finalQuantity * product.getPrice()),
                        materialsList
                ));
            }
        }
        return suggestions;
    }
}