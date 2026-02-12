package com.example.autoflex.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductComposition> compositions;

    // Adição recomendada: Método utilitário para garantir que a relação
    // seja salva corretamente no banco de ambos os lados.
    public void setCompositions(List<ProductComposition> compositions) {
        this.compositions = compositions;
        if (compositions != null) {
            for (ProductComposition comp : compositions) {
                comp.setProduct(this);
            }
        }
    }
}