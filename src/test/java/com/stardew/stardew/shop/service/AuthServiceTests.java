package com.stardew.stardew.shop.service;

import com.stardew.shop.model.User;
import com.stardew.shop.service.AuthService;
import com.stardew.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {

    @Mock
    private UserService userService;
    @InjectMocks
    private AuthService authService;
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void findByCredentialsSuccess() {
        User mockUser = new User("example@email.com", "example", "Another","Example", "United States");
        when(userService.findByCredentials("example@email.com", "password")).thenReturn(Optional.of(mockUser));
        Optional<User> resultUser = userService.findByCredentials("example@email.com", "password");
        assertEquals(resultUser.get().getEmail(), mockUser.getEmail());
        assertEquals(resultUser.get().getPassword(), mockUser.getPassword());

    }

    @Test
    void findByCredentialsFail() {
        User mockUser = new User("new@email.com", "new", "New","Test", "United States");
        when(userService.findByCredentials("new@email.com", "new")).thenReturn(Optional.of(mockUser));
        Optional<User> resultUser = userService.findByCredentials("new@email.com", "new");
        assertNotEquals("com.email@new", mockUser.getEmail());
        assertNotEquals("wen", mockUser.getPassword());
        assertNotEquals(resultUser.get().getEmail(), "com.email@new");
        assertNotEquals(resultUser.get().getPassword(), "wen");
    }

}
