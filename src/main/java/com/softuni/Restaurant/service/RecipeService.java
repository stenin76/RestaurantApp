package com.softuni.Restaurant.service;

import com.softuni.Restaurant.model.Product;
import com.softuni.Restaurant.model.Recipe;
import com.softuni.Restaurant.model.dto.RecipeAddDto;
import com.softuni.Restaurant.repository.ProductRepository;
import com.softuni.Restaurant.repository.RecipeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    private final ProductRepository productRepository;

    public RecipeService(RecipeRepository recipeRepository, ProductService productService, ModelMapper modelMapper, ProductRepository productRepository) {
        this.recipeRepository = recipeRepository;
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    public void addRecipe(RecipeAddDto recipeAddDto, Long id) {
        Recipe recipe = modelMapper.map(recipeAddDto, Recipe.class);
        this.recipeRepository.save(recipe);
        Product productToAddRecipe = this.productService.findById(id);
        productToAddRecipe.setRecipe(recipe);
        this.productRepository.save(productToAddRecipe);

    }
}
