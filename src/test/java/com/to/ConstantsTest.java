package com.to;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

    @Test
    void testApiSecretKeyString() {
    	Constants constants = new Constants();
        assertEquals("medicare", constants.API_SECRET_KEY_STRING);
    }

    @Test
    void testTokenValidity() {
        // 10 hours in milliseconds
        long expected = 10 * 60 * 60 * 1000;
        assertEquals(expected, Constants.TOKEN_VALIDITY);
    }
}
