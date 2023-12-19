package com.softuni.Restaurant.service;

import com.softuni.Restaurant.model.Product;
import com.softuni.Restaurant.model.dto.ProductAddDto;
import com.softuni.Restaurant.model.enums.ProductType;
import com.softuni.Restaurant.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public void addProduct(ProductAddDto productAddDto) {
        Product productToAdd = modelMapper.map(productAddDto, Product.class);
        this.productRepository.save(productToAdd);
    }

    public void removeProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    public Set<Product> getAllBurgerProducts() {
        return productRepository.findAllByProductType(ProductType.BURGER);
    }

    public Set<Product> getAllPizzaProducts() {
        return productRepository.findAllByProductType(ProductType.PIZZA);
    }

    public Set<Product> getAllPastaProducts() {
        return productRepository.findAllByProductType(ProductType.PASTA);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return this.productRepository.findById(id).get();
    }

    //FETCH CLIENT

    public List<ProductAddDto> getAllDtoProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductService::mapProductAddDto)
                .toList();
    }

    private static ProductAddDto mapProductAddDto(Product product) {

        ProductAddDto productAddDto = new ProductAddDto();
        productAddDto.setName(product.getName());
        productAddDto.setDescription(product.getDescription());
        productAddDto.setPrice(product.getPrice());
        productAddDto.setImageUrl(product.getImageUrl());
        productAddDto.setProductType(product.getProductType());

        return productAddDto;
    }
}
