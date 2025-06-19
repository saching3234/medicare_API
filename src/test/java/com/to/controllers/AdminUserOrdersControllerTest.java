package com.to.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import com.to.entities.*;
import com.to.services.*;

class AdminUserOrdersControllerTest {
    @InjectMocks
    private AdminUserOrdersController adminUserOrdersController;

    @Mock
    private OrdersService ordersService;
    @Mock
    private UserService userService;
    @Mock
    private DeliveryStatusService deliveryStatusService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDeliveryStatus() {
        List<DeliveryStatus> mockList = Arrays.asList(new DeliveryStatus(), new DeliveryStatus());
        when(deliveryStatusService.getAllDeliveryStatus()).thenReturn(mockList);
        List<DeliveryStatus> result = adminUserOrdersController.getAllDeliveryStatus();
        assertEquals(2, result.size());
        verify(deliveryStatusService).getAllDeliveryStatus();
    }

    @Test
    void testUpdateDeliveryStatus() {
        Orders order = new Orders();
        Orders updatedOrder = new Orders();
        when(ordersService.updateOrder(order)).thenReturn(updatedOrder);
        Orders result = adminUserOrdersController.updateDeliveryStatus(order);
        assertEquals(updatedOrder, result);
        verify(ordersService).updateOrder(order);
    }

    @Test
    void testGetAllOrders() {
        List<Orders> mockOrders = Arrays.asList(new Orders(), new Orders());
        when(ordersService.getAllOrders()).thenReturn(mockOrders);
        List<Orders> result = adminUserOrdersController.getAllOrders();
        assertEquals(2, result.size());
        verify(ordersService).getAllOrders();
    }

    @Test
    void testGetOrderById() {
        Orders order = new Orders();
        when(ordersService.getOrderById(1)).thenReturn(order);
        Orders result = adminUserOrdersController.getOrderById(1);
        assertEquals(order, result);
        verify(ordersService).getOrderById(1);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setUid(1);
        user1.setName("Alice");
        User user2 = new User();
        user2.setUid(2);
        user2.setName("Bob");
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));
        List<TempUser> result = adminUserOrdersController.getAllUsers();
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());
        verify(userService).getAllUsers();
    }
}