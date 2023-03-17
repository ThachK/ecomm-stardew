package com.stardew.shop.model;

import lombok.*;

import javax.persistence.*;
@Entity
@Table(name="products")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "image")
    private String image;

    public Product(String productName, String description, Integer price, String image) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.image = image;
    }
}