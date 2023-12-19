package com.softuni.Restaurant.model.enums;

public enum UserRoles {
    USER("User"),
    ADMIN("Admin");

    private final String value;

    UserRoles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
