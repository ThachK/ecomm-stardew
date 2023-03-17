package com.stardew.stardew.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stardew.shop.controller.AuthController;
import com.stardew.shop.dtos.LoginDTO;
import com.stardew.shop.model.User;
import com.stardew.shop.service.AuthService;
import com.stardew.shop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private AuthService authService;
    @Mock
    private UserService userService;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AuthController(authService))
                .build();
    }
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void loginSuccess() throws Exception {
        User testUser2 = new User("blorp@email.com", "blorp", "Bob", "Smith", "Mexico");
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("blorp@email.com");
        loginDTO.setPassword("blorp");
        String requestBody = objectMapper.writeValueAsString(loginDTO);

        given(authService.findByCredentials(loginDTO.getEmail(), loginDTO.getPassword())).willReturn(testUser2);
        this.mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testUser2.getId())))
                .andExpect(jsonPath("$.email", is(testUser2.getEmail())))
                .andExpect(jsonPath("$.password", is(testUser2.getPassword())))
                .andExpect(jsonPath("$.firstname", is(testUser2.getFirstname())))
                .andExpect(jsonPath("$.lastname", is(testUser2.getLastname())))
                .andExpect(jsonPath("$.country", is(testUser2.getCountry()))).andDo(print());
    }
}
