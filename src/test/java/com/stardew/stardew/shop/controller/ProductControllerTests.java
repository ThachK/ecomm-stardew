package com.stardew.stardew.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stardew.shop.controller.ProductController;
import com.stardew.shop.model.Product;
import com.stardew.shop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private ProductService productService;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productService))
                .build();
    }
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetAllPosts() throws Exception {
        List<Product> expectedList = new ArrayList<>();
        Product expectedProduct = new Product("TestItem", "Description", 100, "testItemImage");

        expectedList.add(expectedProduct);

        given(productService.getAll()).willReturn(expectedList);

        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(expectedList.size())));
    }

    @Test
    void testGetProductById() throws Exception {
        Product testProduct = new Product("TestItem", "Description", 100, "testItemImage");
        testProduct.setId(4);
        Optional<Product> optionalProduct = Optional.of(testProduct);
        List<Product> products = new ArrayList<>();
        given(productService.findById(4)).willReturn(optionalProduct);
        this.mockMvc.perform(get("/products/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testProduct.getId())));
    }

}
