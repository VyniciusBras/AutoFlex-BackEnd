package com.example.autoflex.service;

import com.example.autoflex.dto.ProductionSuggestionDTO;
import com.example.autoflex.model.Product;
import com.example.autoflex.model.ProductComposition;
import com.example.autoflex.model.RawMaterial;
import com.example.autoflex.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductionServiceTest {

    @Autowired
    private ProductionService productionService;

    @MockitoBean
    private ProductRepository productRepository;

    @Test
    void shouldCalculateFiveUnitsWhenStockAllows() {
        // Cenário: Criar um material com 10 unidades em estoque
        RawMaterial iron = new RawMaterial();
        iron.setName("Iron");
        iron.setStockQuantity(10.0);

        // Criar um produto que exige 2 unidades de ferro por item
        Product axe = new Product();
        axe.setName("Iron Axe");
        axe.setPrice(100.0);

        ProductComposition comp = new ProductComposition();
        comp.setRawMaterial(iron);
        comp.setQuantityRequired(2.0);
        comp.setProduct(axe);

        axe.setCompositions(Arrays.asList(comp));

        // Simular o banco de dados retornando este produto
        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(axe));

        // Executar o cálculo
        List<ProductionSuggestionDTO> suggestions = productionService.calculateSuggestions();

        // Verificação: 10/2 = 5 unidades possíveis
        assertEquals(1, suggestions.size());
        assertEquals("Iron Axe", suggestions.get(0).getProductName());
        assertEquals(5, suggestions.get(0).getQuantityPossible());
    }
}