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
    private ProductionService productionService; // Nome corrigido aqui

    @Test
    @DisplayName("Deve calcular corretamente que é possível produzir 5 unidades")
    void shouldCalculateCorrectProductionQuantity() {
        // GIVEN: Um produto que pede 2 madeiras, e temos 10 no estoque
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

        // WHEN: Executamos o cálculo
        List<ProductionSuggestionDTO> result = productionService.calculateSuggestions();

        // THEN: 10 / 2 = 5 unidades possíveis
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(5, result.get(0).getQuantityPossible());
        Assertions.assertEquals(250.0, result.get(0).getTotalPrice()); // 5 * 50.0
    }
}