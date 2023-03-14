package com.stardew.shop.controller;

import com.stardew.shop.dtos.LoginDTO;
import com.stardew.shop.dtos.RegisterDTO;
import com.stardew.shop.model.User;
import com.stardew.shop.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;


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
        Optional<User> userOptional = authService.findByCredentials(loginDTO.getEmail(), loginDTO.getPassword());

        if(!userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("user", userOptional.get());

        return ResponseEntity.ok(userOptional.get());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO) {
        User created = new User(
                registerDTO.getEmail(),
                registerDTO.getPassword(),
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                registerDTO.getCountry());

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(created));
    }
}
