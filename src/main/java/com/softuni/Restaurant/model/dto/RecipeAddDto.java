package com.softuni.Restaurant.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RecipeAddDto {

    @NotBlank(message = "Ingredients cannot be empty!")
    @Size(min = 2, max = 200, message = "Ingredients length must be between 2 and 50 characters!")
    private String ingredients;

    @NotBlank(message = "Preparation cannot be empty!")
    @Size(min = 2, max = 200, message = "Preparation length must be between 2 and 50 characters!")
    private String methodOfPreparation;

    public RecipeAddDto() {}

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMethodOfPreparation() {
        return methodOfPreparation;
    }

    public void setMethodOfPreparation(String methodOfPreparation) {
        this.methodOfPreparation = methodOfPreparation;
    }
}
