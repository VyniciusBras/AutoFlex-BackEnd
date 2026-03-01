package com.example.autoflex.service;

import com.example.autoflex.dto.ProductionSuggestionDTO;
import com.example.autoflex.model.Product;
import com.example.autoflex.model.ProductComposition;
import com.example.autoflex.model.RawMaterial;
import com.example.autoflex.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductionServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductionService productionService;

    @Test
    @DisplayName("The test should correctly calculate that it is possible to produce 5 units.")
    void shouldCalculateCorrectProductionQuantity() {

        RawMaterial wood = new RawMaterial();
        wood.setName("Wood");
        wood.setStockQuantity(10.0);

        ProductComposition comp = new ProductComposition();
        comp.setRawMaterial(wood);
        comp.setQuantityRequired(2.0);

        Product axe = new Product();
        axe.setName("Iron Axe");
        axe.setPrice(50.0);
        axe.setCompositions(List.of(comp));

        Mockito.when(productRepository.findAll()).thenReturn(List.of(axe));

        List<ProductionSuggestionDTO> result = productionService.calculateSuggestions();

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(5, result.getFirst().getQuantityPossible());
        Assertions.assertEquals(250.0, result.getFirst().getTotalPrice()); // 5 * 50.0
    }
}