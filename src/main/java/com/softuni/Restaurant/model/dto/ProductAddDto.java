package com.softuni.Restaurant.model.dto;

import com.softuni.Restaurant.model.enums.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductAddDto {

    @NotBlank(message = "Name cannot be empty!")
    @Size(min = 2, max = 40, message = "Name length must be between 2 and 50 characters!")
    private String name;

    @NotBlank(message = "Description cannot be empty!")
    @Size(min = 2, max = 40, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @NotNull(message = "Price cannot be empty!")
    @Positive(message = "Price must be positive number!")
    private BigDecimal price;

    @NotBlank(message = "ImageUrl cannot be empty!")
    private String imageUrl;

    @NotNull(message = "You must select a product type!")
    private ProductType productType;

    public ProductAddDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "ProductAddDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", productType=" + productType +
                '}';
    }
}
