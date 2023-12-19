package com.softuni.Restaurant.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OrderAddDto {

    @NotBlank(message = "Address cannot be empty!")
    @Size(min = 2, max = 40, message = "address length must be between 2 and 50 characters!")
    private String address;

    @NotBlank(message = "Number cannot be empty!")
    @Size(min = 2, max = 10, message = "Number length must be between 2 and 10 characters!")
    private String number;

    public OrderAddDto() {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
