package com.softuni.Restaurant.service;

import com.softuni.Restaurant.model.Order;
import com.softuni.Restaurant.model.UserEntity;
import com.softuni.Restaurant.model.dto.OrderAddDto;
import com.softuni.Restaurant.repository.OrderRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ShoppingCartService shoppingCartService;

    private final UserService userService;

    public OrderService(OrderRepository orderRepository, ShoppingCartService shoppingCartService, UserService userService) {
        this.orderRepository = orderRepository;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    public void addOrder(OrderAddDto orderAddDto) {
        Order orderToAdd = new Order();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity owner = userService.findByUserName(auth.getName());

        orderToAdd.setAddress(orderAddDto.getAddress());
        orderToAdd.setNumber(orderAddDto.getNumber());
        orderToAdd.setOwnByUser(owner);
        orderToAdd.setTotalPrice(shoppingCartService.totalPrice());
        orderToAdd.setProducts(shoppingCartService.getProductsFromCartInList());

        this.orderRepository.save(orderToAdd);
    }

    public List<Order> getAllOrders () {
        return orderRepository.findAll();
    }
}
