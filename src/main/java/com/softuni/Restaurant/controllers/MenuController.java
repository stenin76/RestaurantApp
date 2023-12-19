package com.softuni.Restaurant.controllers;

import com.softuni.Restaurant.model.Product;
import com.softuni.Restaurant.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Set;

@Controller
public class MenuController {

    private final ProductService productService;

    public MenuController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/menu")
    public String menuIni(Model model) {

        Set<Product> burgerProducts = this.productService.getAllBurgerProducts();
        model.addAttribute("burgerProducts", burgerProducts);

        Set<Product> pizzaProducts = this.productService.getAllPizzaProducts();
        model.addAttribute("pizzaProducts", pizzaProducts);

        Set<Product> pastaProducts = this.productService.getAllPastaProducts();
        model.addAttribute("pastaProducts", pastaProducts);

        return "menu";
    }
}
