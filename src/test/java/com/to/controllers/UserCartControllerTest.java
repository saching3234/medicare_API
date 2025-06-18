package com.to.controllers;

import com.to.entities.Cart;
import com.to.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserCartControllerTest {
    @Mock
    private CartService cartService;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private UserCartController userCartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCartDetails() {
        when(request.getAttribute("userId")).thenReturn(1);
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        when(cartService.getCartsByUserId(1)).thenReturn(Arrays.asList(cart1, cart2));
        List<Cart> result = userCartController.getCartDetails(request);
        assertThat(result).hasSize(2);
        verify(cartService).getCartsByUserId(1);
    }

    @Test
    void testSaveCart() {
        when(request.getAttribute("userId")).thenReturn(2);
        when(request.getAttribute("userName")).thenReturn("testuser");
        Cart cart = new Cart();
        Cart savedCart = new Cart();
        when(cartService.saveProductIntoCart(any(Cart.class), eq(2))).thenReturn(savedCart);
        Cart result = userCartController.saveCart(request, cart);
        assertThat(result).isEqualTo(savedCart);
        verify(cartService).saveProductIntoCart(any(Cart.class), eq(2));
    }

    @Test
    void testDeleteFromCart() {
       when(cartService.deleteProductFromCart(5)).thenReturn("Product Removed from cart: 5");
        userCartController.deleteFromCart(5); 
        verify(cartService).deleteProductFromCart(5);
    }

    @Test
    void testDeleteAllProductsFromCart() {
        when(request.getAttribute("userId")).thenReturn(1);
          when(cartService.removeFromCart(1)).thenReturn("All products removed from cart for user 1");
        userCartController.deleteAllProductsFromCart(request);
        verify(cartService).removeFromCart(1);
    }

    @Test
    void testUpdateQuantity() {
        when(request.getAttribute("userId")).thenReturn(4);
        Cart cart = new Cart();
        Cart updatedCart = new Cart();
        when(cartService.updateQty(any(Cart.class))).thenReturn(updatedCart);
        Cart result = userCartController.updateQuantity(request, cart);
        assertThat(result).isEqualTo(updatedCart);
        verify(cartService).updateQty(any(Cart.class));
    }
}