package com.softuni.Restaurant.model.enums;

public enum ProductType {

    BURGER("Burger"),
    PIZZA("Pizza"),
    PASTA("Pasta");

    private final String value;

    ProductType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
