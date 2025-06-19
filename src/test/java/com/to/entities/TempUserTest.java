package com.to.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TempUserTest {
    @Test
    void testUidGetterAndSetter() {
        TempUser user = new TempUser();
        user.setUid(123);
        assertEquals(123, user.getUid());
    }

    @Test
    void testNameGetterAndSetter() {
        TempUser user = new TempUser();
        user.setName("John Doe");
        assertEquals("John Doe", user.getName());
    }
}
