package com.to.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EtResourceNotFoundExceptionTest {

    @Test
    void testMessageIsSet() {
        String message = "Resource not found!";
        EtResourceNotFoundException exception = new EtResourceNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testIsRuntimeException() {
        EtResourceNotFoundException exception = new EtResourceNotFoundException("msg");
        assertTrue(exception instanceof RuntimeException);
    }
}
