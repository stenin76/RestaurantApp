package com.softuni.Restaurant.controllers;

import com.softuni.Restaurant.model.Order;
import com.softuni.Restaurant.model.Product;
import com.softuni.Restaurant.model.UserEntity;
import com.softuni.Restaurant.service.OrderService;
import com.softuni.Restaurant.service.ProductService;
import com.softuni.Restaurant.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PanelController {

    private final ProductService productService;

    private final UserService userService;

    private final OrderService orderService;

    public PanelController(ProductService productService, UserService userService, OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/product-panel")
    public String productPanel(Model model) {

        List<Product> AllProducts = this.productService.getAllProducts();
        model.addAttribute("allProducts", AllProducts);

        return "product-panel";
    }

    @GetMapping("/order-panel")
    public String orderPanel(Model model) {

        List<Order> AllOrders = this.orderService.getAllOrders();
        model.addAttribute("allOrders", AllOrders);

        return "/order-panel";
    }

    @GetMapping("/users-panel")
    public String usersPanel(Model model) {

        List<UserEntity> allUsers = this.userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);

        return "users-panel";
    }

    @GetMapping("/product/remove/{id}")
    public String removeProduct(@PathVariable Long id){
        this.productService.removeProduct(id);
        return "redirect:/product-panel";
    }

    @GetMapping("/user/addRole/{id}")
    public String addRole(@PathVariable Long id){
        this.userService.addUserRole(id);
        return "redirect:/users-panel";
    }
}
