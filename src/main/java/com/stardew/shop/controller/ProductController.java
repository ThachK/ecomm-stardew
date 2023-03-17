package com.stardew.shop.controller;

import com.stardew.shop.model.Product;
import com.stardew.shop.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable int id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return ResponseEntity.badRequest().body("No products were found with this id.");
        }
        return ResponseEntity.ok(productOptional.get());
    }

}
