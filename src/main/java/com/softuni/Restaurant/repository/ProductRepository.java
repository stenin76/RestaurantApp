package com.softuni.Restaurant.repository;

import com.softuni.Restaurant.model.Product;
import com.softuni.Restaurant.model.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Set<Product> findAllByProductType(ProductType productType);

    Optional<Product> findById(Long id);


    List<Product> findAll ();

}
