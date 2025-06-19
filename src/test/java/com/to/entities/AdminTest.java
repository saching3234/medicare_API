package com.to.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void testGettersAndSetters() {
        Admin admin = new Admin();
        admin.setId(10);
        admin.setAdminId("admin123");
        admin.setPassword("pass@123");

        assertEquals(10, admin.getId());
        assertEquals("admin123", admin.getAdminId());
        assertEquals("pass@123", admin.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        Admin admin = new Admin(1, "admin1", "pwd1");
        assertEquals(1, admin.getId());
        assertEquals("admin1", admin.getAdminId());
        assertEquals("pwd1", admin.getPassword());
    }

    @Test
    void testNoArgsConstructor() {
        Admin admin = new Admin();
        assertNotNull(admin);
    }

    @Test
    void testToString() {
        Admin admin = new Admin(2, "admin2", "pwd2");
        String expected = "Admin [id=2, adminId=admin2, password=pwd2]";
        assertEquals(expected, admin.toString());
    }
}
