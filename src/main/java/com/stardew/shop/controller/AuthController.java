package com.stardew.shop.controller;

import com.stardew.shop.dtos.LoginDTO;
import com.stardew.shop.model.User;
import com.stardew.shop.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        User user = authService.findByCredentials(loginDTO.getEmail(), loginDTO.getPassword());

        if(user == null) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("user", user);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }
}
