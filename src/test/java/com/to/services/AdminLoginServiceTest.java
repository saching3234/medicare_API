package com.to.services;

import com.to.entities.Admin;
import com.to.exceptions.EtAuthException;
import com.to.repositories.AdminLoginRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminLoginServiceTest {

    @Mock
    private AdminLoginRepository adminLoginRepository;

    @InjectMocks
    private AdminLoginService adminLoginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adminLoginCheck_successfulLogin_returnsAdmin() {
        Admin inputAdmin = Instancio.create(Admin.class);
        Admin dbAdmin = new Admin();
        dbAdmin.setAdminId(inputAdmin.getAdminId());
        dbAdmin.setPassword(inputAdmin.getPassword());
        when(adminLoginRepository.findByAdminId(inputAdmin.getAdminId())).thenReturn(dbAdmin);

        Admin result = adminLoginService.adminLoginCheck(inputAdmin);
        assertEquals(dbAdmin, result);
    }

    @Test
    void adminLoginCheck_invalidPassword_throwsException() {
        Admin inputAdmin = Instancio.create(Admin.class);
        Admin dbAdmin = new Admin();
        dbAdmin.setAdminId(inputAdmin.getAdminId());
        dbAdmin.setPassword("differentPassword");
        when(adminLoginRepository.findByAdminId(inputAdmin.getAdminId())).thenReturn(dbAdmin);

        EtAuthException exception = assertThrows(EtAuthException.class, () ->
                adminLoginService.adminLoginCheck(inputAdmin));
        assertTrue(exception.getMessage().contains("Invalid Password"));
    }

    @Test
    void adminLoginCheck_invalidAdminId_throwsException() {
        Admin inputAdmin = Instancio.create(Admin.class);
        when(adminLoginRepository.findByAdminId(inputAdmin.getAdminId())).thenReturn(null);

        EtAuthException exception = assertThrows(EtAuthException.class, () ->
                adminLoginService.adminLoginCheck(inputAdmin));
        assertTrue(exception.getMessage().contains("Invalid Admin Id"));
    }

    @Test
    void isAdminAlreadyRegistered_returnsTrue_whenAdminExists() {
        String adminId = "admin123";
        Admin dbAdmin = new Admin();
        dbAdmin.setAdminId(adminId);
        when(adminLoginRepository.findByAdminId(adminId)).thenReturn(dbAdmin);
        assertTrue(adminLoginService.isAdminAlreadyRegistered(adminId));
    }

    @Test
    void isAdminAlreadyRegistered_returnsFalse_whenAdminDoesNotExist() {
        String adminId = "admin456";
        when(adminLoginRepository.findByAdminId(adminId)).thenReturn(null);
        assertFalse(adminLoginService.isAdminAlreadyRegistered(adminId));
    }

    @Test
    void registerAdmin_successfulRegistration() {
        Admin admin = new Admin();
        admin.setAdminId("admin789");
        when(adminLoginRepository.save(admin)).thenReturn(admin);
        when(adminLoginRepository.findByAdminId("admin789")).thenReturn(admin);
        assertDoesNotThrow(() -> adminLoginService.registerAdmin(admin));
    }

    @Test
    void registerAdmin_throwsException_whenNotRegistered() {
        Admin admin = new Admin();
        admin.setAdminId("admin000");
        when(adminLoginRepository.save(admin)).thenReturn(admin);
        when(adminLoginRepository.findByAdminId("admin000")).thenReturn(null);
        assertThrows(EtAuthException.class, () -> adminLoginService.registerAdmin(admin));
    }
}