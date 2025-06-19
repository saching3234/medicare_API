package com.to.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    @Test
    void testGettersAndSetters() {
        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setUserId(2);
        cart.setUserName("testuser");
        cart.setCid(3);
        cart.setImg_name("image.png");
        cart.setPdescription("desc");
        cart.setPid("P123");
        cart.setPname("Product");
        cart.setPrice_per_unit(100);
        cart.setBrand("BrandX");
        cart.setQuantity(5);

        assertEquals(1, cart.getCartId());
        assertEquals(2, cart.getUserId());
        assertEquals("testuser", cart.getUserName());
        assertEquals(3, cart.getCid());
        assertEquals("image.png", cart.getImg_name());
        assertEquals("desc", cart.getPdescription());
        assertEquals("P123", cart.getPid());
        assertEquals("Product", cart.getPname());
        assertEquals(100, cart.getPrice_per_unit());
        assertEquals("BrandX", cart.getBrand());
        assertEquals(5, cart.getQuantity());
    }

    @Test
    void testAllArgsConstructor() {
        Cart cart = new Cart(1, 2, "testuser", 3, "image.png", "desc", "P123", "Product", 100, "BrandX", 5);
        assertEquals(1, cart.getCartId());
        assertEquals(2, cart.getUserId());
        assertEquals("testuser", cart.getUserName());
        assertEquals(3, cart.getCid());
        assertEquals("image.png", cart.getImg_name());
        assertEquals("desc", cart.getPdescription());
        assertEquals("P123", cart.getPid());
        assertEquals("Product", cart.getPname());
        assertEquals(100, cart.getPrice_per_unit());
        assertEquals("BrandX", cart.getBrand());
        assertEquals(5, cart.getQuantity());
    }

    @Test
    void testToString() {
        Cart cart = new Cart(1, 2, "testuser", 3, "image.png", "desc", "P123", "Product", 100, "BrandX", 5);
        String str = cart.toString();
        assertTrue(str.contains("cartId=1"));
        assertTrue(str.contains("userId=2"));
        assertTrue(str.contains("cid=3"));
        assertTrue(str.contains("img_name=image.png"));
        assertTrue(str.contains("pdescription=desc"));
        assertTrue(str.contains("pid=P123"));
        assertTrue(str.contains("pname=Product"));
        assertTrue(str.contains("price_per_unit=100"));
        assertTrue(str.contains("quantity=5"));
    }
}
