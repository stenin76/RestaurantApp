package com.softuni.Restaurant.service;

import com.softuni.Restaurant.model.Product;
import com.softuni.Restaurant.repository.ProductRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartService {

    private final Map<Product, Integer> productsMap = new HashMap<>();

    private final ProductRepository productRepository;

    public ShoppingCartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProductToCart(Long id) {

        Product productToAdd = this.productRepository.findById(id).get();
        boolean contain = false;

        if (productsMap.isEmpty()) {
            productsMap.put(productToAdd, 1);
        } else {
            for (Map.Entry<Product, Integer> p : productsMap.entrySet()) {
                if (p.getKey().getId().equals(productToAdd.getId())) {
                    productsMap.put(p.getKey(), p.getValue() + 1);
                    contain = true;
                    break;
                }
            }
            if (!contain) {
                productsMap.put(productToAdd, 1);
            }
        }
    }

    public void removeProductFromCart(Long id) {
        Product productToRemove = this.productRepository.findById(id).get();

        for (Map.Entry<Product, Integer> p : productsMap.entrySet()) {
            if (p.getKey().getId().equals(productToRemove.getId())) {
                productsMap.remove(p.getKey(), p.getValue());
                break;
            }
        }
    }

    public void increaseProduct(Long id) {
        Product productToIncreaseQuantity = this.productRepository.findById(id).get();
        for (Map.Entry<Product, Integer> p : productsMap.entrySet()) {
            if (p.getKey().getId().equals(productToIncreaseQuantity.getId())) {
                productsMap.put(p.getKey(), p.getValue() + 1);
                break;
            }
        }
    }

    public void reduceProduct(Long id) {
        Product productToIncreaseQuantity = this.productRepository.findById(id).get();
        for (Map.Entry<Product, Integer> p : productsMap.entrySet()) {
            if (p.getKey().getId().equals(productToIncreaseQuantity.getId())) {
                if (p.getValue() > 1) {
                    productsMap.put(p.getKey(), p.getValue() - 1);
                }else {
                    removeProductFromCart(id);
                }
                break;
            }
        }
    }

    public BigDecimal totalPrice() {
        return productsMap.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(productsMap);
    }

    public List<Product> getProductsFromCartInList() {
        List<Product> productSet = new ArrayList<>();

        for (Map.Entry<Product, Integer> p : productsMap.entrySet()) {
            for (int i = 0; i < p.getValue(); i++) {
                productSet.add(p.getKey());
            }
        }
        productsMap.clear();
        return productSet;
    }
}
