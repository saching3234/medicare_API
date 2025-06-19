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
}
