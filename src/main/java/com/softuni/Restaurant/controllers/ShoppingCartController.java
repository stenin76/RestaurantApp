package com.softuni.Restaurant.controllers;

import com.softuni.Restaurant.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;


    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.totalPrice());
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{id}")
    public String addProductToCart(@PathVariable Long id) {
        this.shoppingCartService.addProductToCart(id);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart/increaseProduct/{id}")
    public String increaseProduct(@PathVariable Long id) {
        this.shoppingCartService.increaseProduct(id);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart/reduceProduct/{id}")
    public String reduceProduct(@PathVariable Long id) {
        this.shoppingCartService.reduceProduct(id);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart/removeProductFromCart/{id}")
    public String removeProductFromCart(@PathVariable Long id) {
        this.shoppingCartService.removeProductFromCart(id);
        return "redirect:/shoppingCart";
    }
}
