package com.to.services;

import com.to.entities.DeliveryStatus;
import com.to.repositories.DeliveryStatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryStatusServiceTest {

    @Mock
    DeliveryStatusRepository deliveryStatusRepository;

    @InjectMocks
    DeliveryStatusService deliveryStatusService;

    @Test
    void testGetAllDeliveryStatus() {
        DeliveryStatus status1 = new DeliveryStatus();
        DeliveryStatus status2 = new DeliveryStatus();
        List<DeliveryStatus> mockList = Arrays.asList(status1, status2);
        when(deliveryStatusRepository.findAll()).thenReturn(mockList);
        List<DeliveryStatus> result = deliveryStatusService.getAllDeliveryStatus();
        assertEquals(2, result.size());
        assertEquals(mockList, result);
    }
}