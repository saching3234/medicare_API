package com.to.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TempProductTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        TempProduct product = new TempProduct(1, 2, "TestName", "TestDesc", 10, 100, true, "img.png", "BrandX");
        assertEquals(1, product.getPid());
        assertEquals(2, product.getCid());
        assertEquals("TestName", product.getPname());
        assertEquals("TestDesc", product.getPdescription());
        assertEquals(10, product.getAvailable_quantity());
        assertEquals(100, product.getPrice_per_unit());
        assertTrue(product.isActive());
        assertEquals("img.png", product.getImg_name());
        assertEquals("BrandX", product.getBrand());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        TempProduct product = new TempProduct();
        product.setPid(5);
        product.setCid(6);
        product.setPname("Name");
        product.setPdescription("Desc");
        product.setAvailable_quantity(20);
        product.setPrice_per_unit(200);
        product.setActive(false);
        product.setImg_name("image.jpg");
        product.setBrand("BrandY");

        assertEquals(5, product.getPid());
        assertEquals(6, product.getCid());
        assertEquals("Name", product.getPname());
        assertEquals("Desc", product.getPdescription());
        assertEquals(20, product.getAvailable_quantity());
        assertEquals(200, product.getPrice_per_unit());
        assertFalse(product.isActive());
        assertEquals("image.jpg", product.getImg_name());
        assertEquals("BrandY", product.getBrand());
    }

    @Test
    void testToString() {
        TempProduct product = new TempProduct(1, 2, "TestName", "TestDesc", 10, 100, true, "img.png", "BrandX");
        String str = product.toString();
        assertTrue(str.contains("pid=1"));
        assertTrue(str.contains("cid=2"));
        assertTrue(str.contains("pname=TestName"));
        assertTrue(str.contains("pdescription=TestDesc"));
        assertTrue(str.contains("available_quantity=10"));
        assertTrue(str.contains("price_per_unit=100"));
        assertTrue(str.contains("active=true"));
        assertTrue(str.contains("img_name=img.png"));
        assertTrue(str.contains("brand=BrandX"));
    }
}
