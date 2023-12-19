package com.softuni.Restaurant.controllers;

import com.softuni.Restaurant.model.dto.RecipeAddDto;
import com.softuni.Restaurant.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RecipeAddController {

    private final RecipeService recipeService;


    public RecipeAddController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    private Long aLong;
    @ModelAttribute("recipeAddDto")
    public RecipeAddDto iniRecipeAddDto() {
        return new RecipeAddDto();
    }

    @GetMapping("/recipe/add/{id}")
    public String addRecipe(@PathVariable Long id) {
        aLong = id;
        return "recipe-add";
    }

    @GetMapping("/recipe-add")
    public String addRecipeTest() {
        return "recipe-add";
    }

    @PostMapping("/recipe/add/{id}")
    public String add(
            @Valid RecipeAddDto recipeAddDto,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("recipeAddDto", recipeAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.recipeAddDto", bindingResult);
            return "redirect:/recipe-add";
        }
        this.recipeService.addRecipe(recipeAddDto ,aLong);

        return "redirect:/menu";
    }
}
