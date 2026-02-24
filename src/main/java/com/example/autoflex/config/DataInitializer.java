package com.example.autoflex.config;

import com.example.autoflex.model.Product;
import com.example.autoflex.model.ProductComposition;
import com.example.autoflex.model.RawMaterial;
import com.example.autoflex.repository.ProductRepository;
import com.example.autoflex.repository.RawMaterialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.ArrayList;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(RawMaterialRepository rmRepo, ProductRepository pRepo) {
        return args -> {

            pRepo.deleteAll();
            rmRepo.deleteAll();

            RawMaterial iron = new RawMaterial();
            iron.setName("Iron");
            iron.setStockQuantity(100.0);

            RawMaterial wood = new RawMaterial();
            wood.setName("Wood");
            wood.setStockQuantity(50.0);

            RawMaterial leather = new RawMaterial();
            leather.setName("Leather");
            leather.setStockQuantity(30.0);

            rmRepo.saveAll(Arrays.asList(iron, wood, leather));

            Product axe = new Product();
            axe.setName("Iron Axe");
            axe.setPrice(150.0);

            ProductComposition comp1 = new ProductComposition();
            comp1.setProduct(axe);
            comp1.setRawMaterial(iron);
            comp1.setQuantityRequired(2.0);

            ProductComposition comp2 = new ProductComposition();
            comp2.setProduct(axe);
            comp2.setRawMaterial(wood);
            comp2.setQuantityRequired(1.0);

            axe.setCompositions(new ArrayList<>(Arrays.asList(comp1, comp2)));

            Product pickaxe = new Product();
            pickaxe.setName("iron pickaxe");
            pickaxe.setPrice(300.0);

            ProductComposition comp3 = new ProductComposition();
            comp3.setProduct(pickaxe);
            comp3.setRawMaterial(iron);
            comp3.setQuantityRequired(5.0);

            ProductComposition comp4 = new ProductComposition();
            comp4.setProduct(pickaxe);
            comp4.setRawMaterial(leather);
            comp4.setQuantityRequired(1.0);

            pickaxe.setCompositions(new ArrayList<>(Arrays.asList(comp3, comp4)));

            pRepo.saveAll(Arrays.asList(axe, pickaxe));

            System.out.println("BACK-END IS RUNNING!");
        };
    }
}