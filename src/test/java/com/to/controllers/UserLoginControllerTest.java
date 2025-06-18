package com.to.controllers;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import com.to.entities.User;
import com.to.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class UserLoginControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserLoginController userLoginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setUid(1);
        user.setEmail("test@example.com");
        user.setName("Test User");
        when(userService.saveUser(any(User.class))).thenReturn(user);
        ResponseEntity<Map<String, String>> response = userLoginController.registerUser(user);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).containsKeys("token", "userName", "userId");
        assertThat(response.getBody().get("userName")).isEqualTo("Test User");
        assertThat(response.getBody().get("userId")).isEqualTo("1");
    }

    @Test
    void testLoginUser() {
        User user = new User();
        user.setUid(2);
        user.setEmail("login@example.com");
        user.setName("Login User");
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("email", "login@example.com");
        userMap.put("password", "password");
        when(userService.userLoginCheck("login@example.com", "password")).thenReturn(user);
        ResponseEntity<Map<String, String>> response = userLoginController.loginUser(userMap);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).containsKeys("token", "userName", "userId");
        assertThat(response.getBody().get("userName")).isEqualTo("Login User");
        assertThat(response.getBody().get("userId")).isEqualTo("2");
    }

    @Test
    void testRegisterUserThrowsException() {
        User user = new User();
        when(userService.saveUser(any(User.class))).thenThrow(new RuntimeException("Register error"));
        assertThatThrownBy(() -> userLoginController.registerUser(user))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Register error");
    }

    @Test
    void testLoginUserThrowsException() {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("email", "fail@example.com");
        userMap.put("password", "fail");
        when(userService.userLoginCheck("fail@example.com", "fail")).thenThrow(new RuntimeException("Login error"));
        assertThatThrownBy(() -> userLoginController.loginUser(userMap))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Login error");
    }
}
