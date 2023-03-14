package com.stardew.shop.service;

import com.stardew.shop.model.User;
import com.stardew.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductService productService;
    @Autowired
    public UserService(UserRepository userRepository, ProductService productService) {
        this.userRepository = userRepository;
        this.productService = productService;
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
