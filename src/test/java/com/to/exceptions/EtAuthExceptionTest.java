package com.to.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtAuthExceptionTest {

    @Test
    void testMessageIsSet() {
        String message = "Unauthorized access!";
        EtAuthException exception = new EtAuthException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testIsRuntimeException() {
        EtAuthException exception = new EtAuthException("msg");
        assertTrue(exception instanceof RuntimeException);
    }
}
