package com.to.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import com.to.entities.Admin;
import com.to.services.AdminLoginService;

class AdminLoginControllerTest {
    @InjectMocks
    private AdminLoginController adminLoginController;

    @Mock
    private AdminLoginService adminLoginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSomeLoginEndpoint() {
        // TODO: Add test logic for AdminLoginController
    }

    @Test
    void testLoginAdmin_ReturnsTokenAndAdminId() {
        Admin inputAdmin = new Admin();
        inputAdmin.setAdminId("admin123");
        inputAdmin.setPassword("password");
        inputAdmin.setId(1);

        Admin returnedAdmin = new Admin();
        returnedAdmin.setAdminId("admin123");
        returnedAdmin.setPassword("password");
        returnedAdmin.setId(1);

        when(adminLoginService.adminLoginCheck(any(Admin.class))).thenReturn(returnedAdmin);

        ResponseEntity<Map<String, String>> response = adminLoginController.loginAdmin(inputAdmin);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("token"));
        assertEquals("admin123", response.getBody().get("adminId"));
    }

    @Test
    void testRegisterAdmin_AdminAlreadyRegistered() {
        Admin admin = new Admin();
        admin.setAdminId("admin123");
        when(adminLoginService.isAdminAlreadyRegistered("admin123")).thenReturn(true);

        ResponseEntity<?> response = adminLoginController.registerAdmin(admin);
        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Admin already registered", response.getBody());
    }

    @Test
    void testRegisterAdmin_Success() {
        Admin admin = new Admin();
        admin.setAdminId("admin456");
        when(adminLoginService.isAdminAlreadyRegistered("admin456")).thenReturn(false);
        doNothing().when(adminLoginService).registerAdmin(admin);

        ResponseEntity<?> response = adminLoginController.registerAdmin(admin);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof Map);
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertTrue(body.containsKey("token"));
        assertEquals("admin456", body.get("adminId"));
    }
}