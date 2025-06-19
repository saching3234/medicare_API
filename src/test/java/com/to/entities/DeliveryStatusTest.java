package com.to.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeliveryStatusTest {

    @Test
    void testGettersAndSetters() {
        DeliveryStatus status = new DeliveryStatus();
        status.setOsid(1);
        status.setStatus("Delivered");

        assertEquals(1, status.getOsid());
        assertEquals("Delivered", status.getStatus());
    }

    @Test
    void testToString() {
        DeliveryStatus status = new DeliveryStatus();
        status.setOsid(2);
        status.setStatus("Pending");
        String expected = "DeliveryStatus [osid=2, status=Pending]";
        assertEquals(expected, status.toString());
    }
}
