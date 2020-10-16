package com.s4n.delivery.core.service;

import com.s4n.delivery.core.repository.DeliveryRouteRepository;
import com.s4n.delivery.core.util.ConstantUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceImplTest {

    @Mock
    private DeliveryRouteRepository deliveryRouteRepository;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    @Test
    void givenANormalExecution_whenInvoked_thenDroneRoutesAreRequested() {
        when(deliveryRouteRepository.findDeliveryRoutesByDrone(anyString()))
                .thenReturn(new ArrayList<>());

        deliveryService.deliver();

        verify(deliveryRouteRepository, times(ConstantUtil.NUM_AVAILABLE_DRONES)).findDeliveryRoutesByDrone(anyString());
    }

}