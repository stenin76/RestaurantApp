package com.softuni.Restaurant.service;

import com.softuni.Restaurant.model.Product;
import com.softuni.Restaurant.model.enums.ProductType;
import com.softuni.Restaurant.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository mockProductRepo;

    @InjectMocks
    private ProductService serviceToTest;


    @Test
    void getAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();

        when(serviceToTest.getAllProducts()).thenReturn(List.of(product1, product2));

        assertEquals(2, serviceToTest.getAllProducts().size());
    }

    @Test
    void getAllBurgerProducts() {

        Product product = new Product();
        Set<Product> productSet = new HashSet<>();
        productSet.add(product);

        when(serviceToTest.getAllBurgerProducts()).thenReturn(productSet);

        assertEquals(1,serviceToTest.getAllBurgerProducts().size());
    }

    @Test
    void getAllPizzaProducts() {

        Product product = new Product();
        Set<Product> productSet = new HashSet<>();
        productSet.add(product);

        when(serviceToTest.getAllPizzaProducts()).thenReturn(productSet);

        assertEquals(1,serviceToTest.getAllPizzaProducts().size());
    }

    @Test
    void getAllPastaProducts() {

        Product product = new Product();
        Set<Product> productSet = new HashSet<>();
        productSet.add(product);

        when(serviceToTest.getAllPastaProducts()).thenReturn(productSet);

        assertEquals(1,serviceToTest.getAllPastaProducts().size());
    }
    private static Product createTestProduct() {
        Product product = new Product();

        product.setId(1L);
        product.setName("B1");
        product.setDescription("bbb");
        product.setProductType(ProductType.BURGER);

        return product;
    }


}

