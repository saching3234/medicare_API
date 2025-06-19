package com.to.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.servlet.http.HttpServletRequest;
import com.to.entities.User;
import com.to.services.UserService;

class UserUpdateControllerTest {
    @InjectMocks
    private UserUpdateController userUpdateController;

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCurrentUserDetails_ReturnsUser() {
        int userId = 42;
        User mockUser = new User();
        mockUser.setUid(userId);
        mockUser.setName("Test User");
        when(request.getAttribute("userId")).thenReturn(userId);
        when(userService.getCurrentUserDetails(userId)).thenReturn(mockUser);

        User result = userUpdateController.getCurrentUserDetails(request);
        assertNotNull(result);
        assertEquals(userId, result.getUid());
        assertEquals("Test User", result.getName());
    }

    @Test
    void testChangeUserDetails_ReturnsUpdatedUser() {
        int userId = 99;
        User inputUser = new User();
        inputUser.setUid(userId);
        inputUser.setName("Updated Name");
        when(request.getAttribute("userId")).thenReturn(userId);
        when(userService.changeUserDetails(userId, inputUser)).thenReturn(inputUser);

        User result = userUpdateController.changeUserDetails(request, inputUser);
        assertNotNull(result);
        assertEquals(userId, result.getUid());
        assertEquals("Updated Name", result.getName());
    }
}