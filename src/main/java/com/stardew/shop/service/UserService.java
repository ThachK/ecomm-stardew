package com.stardew.shop.service;

import com.stardew.shop.dtos.RegisterDTO;
import com.stardew.shop.model.User;
import com.stardew.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User save(RegisterDTO registerDTO) {
        User user = new User(registerDTO.getEmail(), registerDTO.getPassword(), registerDTO.getFirstName(), registerDTO.getLastName(), registerDTO.getCountry());
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}
