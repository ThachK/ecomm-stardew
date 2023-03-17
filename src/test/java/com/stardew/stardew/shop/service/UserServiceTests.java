package com.stardew.stardew.shop.service;

import com.stardew.shop.dtos.RegisterDTO;
import com.stardew.shop.model.User;
import com.stardew.shop.repositories.UserRepository;
import com.stardew.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private User mockedUserObject;

    @InjectMocks
    private UserService userService;

    @Test
    void findByCredentialsSuccess() {
        User mockUser = new User("example@email.com", "example", "Another","Example", "United States");
        when(userRepository.findByEmailAndPassword("example@email.com", "password")).thenReturn(Optional.of(mockUser));
        Optional<User> resultUser = userService.findByCredentials("example@email.com", "password");
        assertEquals(resultUser.get().getEmail(), mockUser.getEmail());
        assertEquals(resultUser.get().getPassword(), mockUser.getPassword());

    }

    @Test
    void findByCredentialsFail() {
        User mockUser = new User("new@email.com", "new", "New","Test", "United States");
        when(userRepository.findByEmailAndPassword("new@email.com", "new")).thenReturn(Optional.of(mockUser));
        Optional<User> resultUser = userService.findByCredentials("new@email.com", "new");
        assertNotEquals("com.email@new", mockUser.getEmail());
        assertNotEquals("wen", mockUser.getPassword());
        assertNotEquals(resultUser.get().getEmail(), "com.email@new");
        assertNotEquals(resultUser.get().getPassword(), "wen");
    }

    @Test
    void saveUserSuccess() {
        RegisterDTO registerDTO = new RegisterDTO("email@email.com", "email", "Email", "Test", "Korea");
        User user = new User("email@email.com", "email", "Email", "Test", "Korea");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User result = userService.save(registerDTO);
        assertEquals(user, result);
    }

    @Test
    void saveUserFail() {
        RegisterDTO registerDTO = new RegisterDTO("email@email.com", "email", "Email", "Test", "Korea");
        User user = new User("email@email.com", "email", "Email", "Test", "Korea");
        when(userRepository.save(any(User.class))).thenReturn(null);
        User result = userService.save(registerDTO);
        assertNotEquals(user, result);
    }

    @Test
    void findByIdSuccess() {
        User mockUser = new User("jdoe@email.com", "password", "John", "Doe", "Brazil");
        mockUser.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));
        Optional<User> resultUser = userService.findById(1);
        assertEquals(resultUser.get().getId(), mockUser.getId());
    }

    @Test
    void findByIdFail() {
        User mockUser = new User("jdoe@email.com", "password", "John", "Doe", "Brazil");
        mockUser.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));
        Optional<User> resultUser = userService.findById(1);
        assertNotEquals(resultUser.get().getId(), 100);
        assertNotEquals(100, mockUser.getId());
    }

    @Test
    void getAllUsersSuccess() {
        //creating empty ArrayList
        List<User> expectedList = new ArrayList<User>();
        //adding mockedUserObject to the list of users
        expectedList.add(mockedUserObject);
        //testing Repository method, when findAll is mocked it should return our expectedList
        when(userRepository.findAll()).thenReturn(expectedList);
        //using the user Service method and saving the result as an ArrayList
        List<User> resultList = userService.getAll();
        //using asserEquals to compare the expected size (1) to the actual result size
        assertEquals(1, resultList.size());
        //using assertEquals to compare the mockUser from our expectedList to the 1st user returned from
        //our actual result list
        assertEquals(expectedList.get(0), resultList.get(0));
    }

    @Test
    void getAllUsersFail() {
        //creating empty ArrayList
        List<User> expectedList = new ArrayList<User>();
        //adding mockedUserObject to the list of users
        expectedList.add(mockedUserObject);
        //testing Repository method, when findAll is mocked it should return our expectedList
        when(userRepository.findAll()).thenReturn(expectedList);
        //using the user Service method and saving the result as an ArrayList
        List<User> resultList = userService.getAll();
        //using assertEquals to compare the expected size (1000) to the actual result size
        assertNotEquals(1000, resultList.size());
        assertNotEquals(expectedList.size(), 1000);
        //using assertEquals to compare the mockUser from our expectedList to the 1st user returned from
        //our actual result list
        assertNotEquals(null, resultList.get(0));
        assertNotEquals(expectedList.get(0), null);
    }

}
