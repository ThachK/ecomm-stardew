package com.stardew.shop.service;

import com.stardew.shop.model.Product;
import com.stardew.shop.model.User;
import com.stardew.shop.repositories.ProductRepository;
import com.stardew.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    private final UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

}
