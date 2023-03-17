package com.stardew.stardew.shop.service;


import com.stardew.shop.model.Product;
import com.stardew.shop.repositories.ProductRepository;
import com.stardew.shop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private Product mockedProduct;

    @InjectMocks
    private ProductService productService;

    @Test
    void findByIdSuccess() {
        Product mockProduct = new Product(2,"Wild Horseradish", "A spicy root found in the spring.", 50, "https://stardewvalleywiki.com/mediawiki/images/9/90/Wild_Horseradish.png");
        when(productRepository.findById(2)).thenReturn(Optional.of(mockProduct));
        Optional<Product> resultProduct = productService.findById(2);
        assertEquals(resultProduct.get().getId(), mockProduct.getId());
    }

    @Test
    void findByIdFail() {
        Product mockProduct = new Product(2,"Wild Horseradish", "A spicy root found in the spring.", 50, "https://stardewvalleywiki.com/mediawiki/images/9/90/Wild_Horseradish.png");
        when(productRepository.findById(2)).thenReturn(Optional.of(mockProduct));
        Optional<Product> resultProduct = productService.findById(2);
        assertNotEquals(resultProduct.get(), mockProduct.getId());
    }

    @Test
    void getAllSuccess() {
        List<Product> expectedList = new ArrayList<>();
        expectedList.add(mockedProduct);
        when(productRepository.findAll()).thenReturn(expectedList);
        List<Product> resultList = productService.getAll();
        assertEquals(1, resultList.size());
        assertEquals(mockedProduct, resultList.get(0));

    }

    @Test
    void getAllFail() {
        List<Product> expectedList = new ArrayList<>();
        expectedList.add(mockedProduct);
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        List<Product> resultList = productService.getAll();
        assertNotEquals(1, resultList.size());
    }
}
