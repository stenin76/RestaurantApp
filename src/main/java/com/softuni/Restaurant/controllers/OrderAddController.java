package com.softuni.Restaurant.controllers;

import com.softuni.Restaurant.model.dto.OrderAddDto;
import com.softuni.Restaurant.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderAddController {

    private final OrderService orderService;

    public OrderAddController(OrderService orderService) {
        this.orderService = orderService;
    }


    @ModelAttribute("orderAddDto")
    public OrderAddDto iniOrderAddDto() {
        return new OrderAddDto();
    }

    @GetMapping("/order-add")
    public String addOrder() {
        return "order-add";
    }

    @PostMapping("/order-add")
    public String addOrder(@Valid OrderAddDto orderAddDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("orderAddDto", orderAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderAddDto", bindingResult);
            return "redirect:/order-add";
        }
        this.orderService.addOrder(orderAddDto);
        return "redirect:/menu";
    }
}
