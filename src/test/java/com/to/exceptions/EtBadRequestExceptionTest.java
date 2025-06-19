package com.to.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EtBadRequestExceptionTest {

    @Test
    void testMessageIsSet() {
        String message = "Bad request!";
        EtBadRequestException exception = new EtBadRequestException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testIsRuntimeException() {
        EtBadRequestException exception = new EtBadRequestException("msg");
        assertTrue(exception instanceof RuntimeException);
    }
}
