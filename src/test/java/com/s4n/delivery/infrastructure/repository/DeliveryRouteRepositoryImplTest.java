package com.s4n.delivery.infrastructure.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryRouteRepositoryImplTest {

    @Mock
    private FindRoutesInFileHelper findRoutesInFileHelper;

    @InjectMocks
    private DeliveryRouteRepositoryImpl deliveryRouteRepository;

    @Test
    void givenANormalExecution_whenFindDeliveryRoutesByDrone_thenFindRoutesInFileHelperIsCalled() {
        final String droneId = "01";

        when(findRoutesInFileHelper.findRoutesByDrone(droneId)).thenReturn(new ArrayList<>());

        deliveryRouteRepository.findDeliveryRoutesByDrone(droneId);

        verify(findRoutesInFileHelper).findRoutesByDrone(droneId);
    }


}