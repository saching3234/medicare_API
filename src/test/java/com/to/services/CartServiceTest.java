package com.to.services;

import com.to.entities.Cart;
import com.to.repositories.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {
    @Mock
    private CartRepository cartRepository;
    @Mock
    private EntityManager em;
    @Mock
    private TypedQuery<Cart> typedQuery;
    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartService = new CartService();
        cartService.cartRepository = cartRepository;
        cartService.em = em;
    }

    @Test
    void testGetCartsByUserId() {
        Cart cart = new Cart();
        cart.setUserId(1);
        when(cartRepository.getCartsByuserId(1)).thenReturn(Arrays.asList(cart));
        List<Cart> result = cartService.getCartsByUserId(1);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getUserId());
    }

    @Test
    void testSaveProductIntoCart_NewProduct() {
        Cart cart = new Cart();
        cart.setPid("101");
        cart.setUserId(1);
        cart.setQuantity(2);
        when(em.createQuery(anyString(), eq(Cart.class))).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenThrow(new RuntimeException());
        when(cartRepository.save(cart)).thenReturn(cart);
        Cart result = cartService.saveProductIntoCart(cart, 1);
        assertEquals(cart, result);
    }

    @Test
    void testSaveProductIntoCart_ExistingProduct() {
        Cart cart = new Cart();
        cart.setPid("101");
        cart.setUserId(1);
        cart.setQuantity(2);
        Cart existing = new Cart();
        existing.setPid("101");
        existing.setUserId(1);
        existing.setQuantity(3);
        when(em.createQuery(anyString(), eq(Cart.class))).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(existing);
        when(cartRepository.save(existing)).thenReturn(existing);
        Cart result = cartService.saveProductIntoCart(cart, 1);
        assertEquals(5, result.getQuantity());
    }

    @Test
    void testDeleteProductFromCart() {
        String result = cartService.deleteProductFromCart(10);
        verify(cartRepository, times(1)).deleteById(10);
        assertTrue(result.contains("Product Removed from cart: 10"));
    }

    @Test
    void testUpdateQty() {
        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setQuantity(5);
        Cart existing = new Cart();
        existing.setCartId(1);
        existing.setQuantity(2);
        when(cartRepository.findById(1)).thenReturn(Optional.of(existing));
        when(cartRepository.save(existing)).thenReturn(existing);
        Cart result = cartService.updateQty(cart);
        assertEquals(5, result.getQuantity());
    }

    @Test
    void testRemoveFromCart() {
        String result = cartService.removeFromCart(1);
        verify(cartRepository, times(1)).deleteCartsByuserId(1);
        assertTrue(result.contains("Cart Moved to oder Placed"));
    }
    
    @Test
    void findByuserIdAndpidTest() {
    	Cart byuserIdAndpid = cartService.findByuserIdAndpid(1, 101);
    	assertNull(byuserIdAndpid, "Expected null since method is not implemented");
    }
}
