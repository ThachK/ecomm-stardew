package com.stardew.shop.controller;

import com.stardew.shop.model.User;
import com.stardew.shop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.save(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable int id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("No users were found with this id.");
        }
        return ResponseEntity.ok(userOptional.get());
    }


}
