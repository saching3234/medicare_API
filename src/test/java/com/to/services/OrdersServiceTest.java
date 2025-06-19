package com.to.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.to.entities.Orders;
import com.to.repositories.OrdersRepository;

class OrdersServiceTest {
    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private OrdersService ordersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateOrder() {
        Orders order = Instancio.create(Orders.class);
        Orders existingOrder = Instancio.create(Orders.class);
        existingOrder.setOrderId(order.getOrderId());
        when(ordersRepository.findById(order.getOrderId())).thenReturn(Optional.of(existingOrder));
        when(ordersRepository.save(any(Orders.class))).thenAnswer(i -> i.getArgument(0));

        Orders updated = ordersService.updateOrder(order);
        assertThat(updated.getDeliveryStatus()).isEqualTo(order.getDeliveryStatus());
        assertThat(updated.getDeliveryDate()).isNotNull();
        verify(ordersRepository).save(existingOrder);
    }

    @Test
    void testSaveOrder() {
        Orders order = Instancio.create(Orders.class);
        when(ordersRepository.save(order)).thenReturn(order);
        Orders saved = ordersService.saveOrder(order);
        assertThat(saved).isEqualTo(order);
    }

    @Test
    void testSaveOrders() {
        List<Orders> orders = Instancio.ofList(Orders.class).size(3).create();
        when(ordersRepository.saveAll(orders)).thenReturn(orders);
        List<Orders> saved = ordersService.saveOrders(orders);
        assertThat(saved).hasSize(3);
    }

    @Test
    void testGetCurrentUsersOrders() {
        int userId = 1;
        List<Orders> orders = Instancio.ofList(Orders.class).size(2).create();
        when(ordersRepository.getOrdersByuserId(userId)).thenReturn(orders);
        List<Orders> result = ordersService.getCurrentUsersOrders(userId);
        assertThat(result).hasSize(2);
    }

    @Test
    void testGetAllOrders() {
        List<Orders> orders = Instancio.ofList(Orders.class).size(4).create();
        when(ordersRepository.findAll()).thenReturn(orders);
        List<Orders> result = ordersService.getAllOrders();
        assertThat(result).hasSize(4);
    }

    @Test
    void testGetOrderById() {
        Orders order = Instancio.create(Orders.class);
        when(ordersRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
        Orders found = ordersService.getOrderById(order.getOrderId());
        assertThat(found).isEqualTo(order);
    }
}
