package com.to.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.to.entities.Orders;
import com.to.services.OrdersService;

class UserOrdersControllerTest {
    @Mock
    private OrdersService ordersService;

    @InjectMocks
    private UserOrdersController userOrdersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAllOrders() {
        Orders order = new Orders();
        order.setQuantity(2);
        order.setPrice_per_unit(100);
        List<Orders> inputOrders = Arrays.asList(order);
        Orders savedOrder = new Orders();
        savedOrder.setQuantity(2);
        savedOrder.setPrice_per_unit(100);
        savedOrder.setTotal(200);
        List<Orders> savedOrders = Arrays.asList(savedOrder);
        when(ordersService.saveOrders(anyList())).thenReturn(savedOrders);

        List<Orders> result = userOrdersController.saveAllOrders(any(), inputOrders);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(200, result.get(0).getTotal());
        verify(ordersService, times(1)).saveOrders(anyList());
    }

    @Test
    void testSaveOrder() {
        Orders order = new Orders();
        order.setQuantity(1);
        order.setPrice_per_unit(50);
        when(ordersService.saveOrder(any(Orders.class))).thenReturn(order);
        Orders result = userOrdersController.saveOrder(order);
        assertNotNull(result);
        assertEquals(1, result.getQuantity());
        assertEquals(50, result.getPrice_per_unit());
        verify(ordersService, times(1)).saveOrder(any(Orders.class));
    }

    @Test
    void testGetAllOrders() {
        Orders order = new Orders();
        order.setQuantity(1);
        order.setPrice_per_unit(50);
        List<Orders> orders = Arrays.asList(order);
        when(ordersService.getCurrentUsersOrders(eq(1))).thenReturn(orders);
        HttpServletRequest request =mock(HttpServletRequest.class);
		when(request.getAttribute("userId")).thenReturn(1);
        List<Orders> result = userOrdersController.getAllOrders(request);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getQuantity());
        verify(ordersService, times(1)).getCurrentUsersOrders(eq(1));
    }
}