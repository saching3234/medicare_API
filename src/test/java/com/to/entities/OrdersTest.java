package com.to.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {
    private Orders orders;

    @BeforeEach
    void setUp() {
        orders = new Orders();
    }

    @Test
    void testSetAndGetOrderId() {
        orders.setOrderId(10);
        assertEquals(10, orders.getOrderId());
    }

    @Test
    void testSetAndGetUserId() {
        orders.setUserId(5);
        assertEquals(5, orders.getUserId());
    }

    @Test
    void testSetAndGetUserName() {
        orders.setUserName("John Doe");
        assertEquals("John Doe", orders.getUserName());
    }

    @Test
    void testSetAndGetImgName() {
        orders.setImg_name("image.png");
        assertEquals("image.png", orders.getImg_name());
    }

    @Test
    void testSetAndGetPdescription() {
        orders.setPdescription("desc");
        assertEquals("desc", orders.getPdescription());
    }

    @Test
    void testSetAndGetPid() {
        orders.setPid(100);
        assertEquals(100, orders.getPid());
    }

    @Test
    void testSetAndGetPname() {
        orders.setPname("Medicine");
        assertEquals("Medicine", orders.getPname());
    }

    @Test
    void testSetAndGetPricePerUnit() {
        orders.setPrice_per_unit(50);
        assertEquals(50, orders.getPrice_per_unit());
    }

    @Test
    void testSetAndGetQuantity() {
        orders.setQuantity(3);
        assertEquals(3, orders.getQuantity());
    }

    @Test
    void testSetAndGetTotal() {
        orders.setPrice_per_unit(20);
        orders.setQuantity(4);
        orders.setTotal(0); // setTotal will recalculate
        assertEquals(80, orders.getTotal());
    }

    @Test
    void testSetAndGetDeliveryStatus() {
        orders.setDeliveryStatus("shipped");
        assertEquals("shipped", orders.getDeliveryStatus());
    }

    @Test
    void testSetAndGetOrderDate() {
        Date now = new Date();
        orders.setOrderDate(now);
        assertEquals(now, orders.getOrderDate());
    }

    @Test
    void testSetAndGetDeliveryDate() {
        Date delivery = new Date();
        orders.setDeliveryDate(delivery);
        assertEquals(delivery, orders.getDeliveryDate());
    }

    @Test
    void testSetAndGetBrand() {
        orders.setBrand("BrandX");
        assertEquals("BrandX", orders.getBrand());
    }
}
