package com.softuni.Restaurant.controllers;


import com.softuni.Restaurant.model.dto.ProductAddDto;
import com.softuni.Restaurant.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductAddDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllDtoProducts());
    }
}
