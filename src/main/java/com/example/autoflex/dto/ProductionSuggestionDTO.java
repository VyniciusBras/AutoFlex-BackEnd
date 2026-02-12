package com.example.autoflex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductionSuggestionDTO {
    private String productName;
    private Integer quantityPossible;
    private Double totalPrice;
    private List<MaterialUsedDTO> materialsUsed;
}