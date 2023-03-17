package com.stardew.shop.service;

import com.stardew.shop.exception.LoginException;
import com.stardew.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;
    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public User findByCredentials(String email, String password) throws LoginException {
        Optional<User> user = userService.findByCredentials(email, password);
        if (!user.isPresent()){
            throw new LoginException("User not found.");
        }
        return user.get();
    }
}
