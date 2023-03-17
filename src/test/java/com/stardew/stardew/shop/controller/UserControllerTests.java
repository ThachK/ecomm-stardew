package com.stardew.stardew.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stardew.shop.controller.UserController;
import com.stardew.shop.dtos.RegisterDTO;
import com.stardew.shop.model.User;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService))
                .build();
    }
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCreateUser() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("email@email.com", "email", "Email", "Test", "Korea");
        User user = new User("email@email.com", "email", "Email", "Test", "Korea");
        when(userService.save(any(RegisterDTO.class))).thenReturn(user);

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("email@email.com"))
                .andExpect(jsonPath("$.password").value("email"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        User testUser1 = new User("test.com", "password", "John", "Doe", "JDoe");
        List<User> userList = new ArrayList<>();
        userList.add(testUser1);

        given(userService.getAll()).willReturn(userList);

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userList.size())));
    }

    @Test
    void testGetUserById() throws Exception {
        User testUser2 = new User("tes2t@email.com", "password2", "Bob", "Smith", "Saudi Arabia");

        given(userService.findById(2)).willReturn(Optional.of(testUser2));
        this.mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testUser2.getId())));
    }

}
