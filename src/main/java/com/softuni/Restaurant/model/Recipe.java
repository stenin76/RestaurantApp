package com.softuni.Restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "recipes")
public class    Recipe extends BaseEntity{


    @Column(columnDefinition = "TEXT")
    private String ingredients;

    @Column(columnDefinition = "TEXT")
    private String methodOfPreparation;

    @OneToOne(mappedBy = "recipe")
    private Product productRecipe;

    public Recipe() {}

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

    public Product getProductRecipe() {
        return productRecipe;
    }

    public void setProductRecipe(Product productRecipe) {
        this.productRecipe = productRecipe;
    }
}
