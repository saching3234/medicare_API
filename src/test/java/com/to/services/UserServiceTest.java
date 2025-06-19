package com.to.services;

import com.to.entities.User;
import com.to.exceptions.EtAuthException;
import com.to.exceptions.EtResourceNotFoundException;
import com.to.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCurrentUserDetails_UserExists() {
        User user = new User();
        user.setUid(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        User result = userService.getCurrentUserDetails(1);
        assertNotNull(result);
        assertEquals(1, result.getUid());
    }

    @Test
    void testGetCurrentUserDetails_UserNotExists() {
        when(userRepository.findById(2)).thenReturn(Optional.empty());
        User result = userService.getCurrentUserDetails(2);
        assertNull(result);
    }

    @Test
    void testSaveUser_Success() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.saveUser(user);
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void testSaveUser_EmailAlreadyExists() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(new User());
        assertThrows(EtAuthException.class, () -> userService.saveUser(user));
    }

    @Test
    void testUserLoginCheck_Success() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        User result = userService.userLoginCheck("test@example.com", "password");
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void testUserLoginCheck_InvalidPassword() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        assertThrows(EtAuthException.class, () -> userService.userLoginCheck("test@example.com", "wrong"));
    }

    @Test
    void testUserLoginCheck_UserNotFound() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(null);
        assertThrows(EtAuthException.class, () -> userService.userLoginCheck("notfound@example.com", "password"));
    }

    @Test
    void testChangeUserDetails_Success() {
        User existingUser = new User();
        existingUser.setUid(1);
        User newUser = new User();
        newUser.setAddress("New Address");
        newUser.setAge(30);
        newUser.setEmail("new@example.com");
        newUser.setGender("M");
        newUser.setName("New Name");
        newUser.setPhone("1234567890");
        newUser.setPincode("123456");
        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);
        User result = userService.changeUserDetails(1, newUser);
        assertNotNull(result);
        assertEquals("New Address", result.getAddress());
        assertEquals(30, result.getAge());
        assertEquals("new@example.com", result.getEmail());
    }

    @Test
    void testChangeUserDetails_UserNotFound() {
        User newUser = new User();
        when(userRepository.findById(2)).thenReturn(Optional.empty());
        assertThrows(EtResourceNotFoundException.class, () -> userService.changeUserDetails(2, newUser));
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
    }
}
