package com.softuni.Restaurant.controllers;

import com.softuni.Restaurant.model.dto.ProductAddDto;
import com.softuni.Restaurant.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductAddController {

    private final ProductService productService;

    public ProductAddController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("productAddDto")
    public ProductAddDto iniProductAddDto() {
        return new ProductAddDto();
    }

    @GetMapping("/product-add")
    public String addProduct() {
        return "product-add";
    }

    @PostMapping("/product-add")
    public String add(@Valid ProductAddDto productAddDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("productAddDto", productAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.productAddDto", bindingResult);
            return "redirect:/product-add";
        }
        this.productService.addProduct(productAddDto);

        return "redirect:/menu";
    }
}
