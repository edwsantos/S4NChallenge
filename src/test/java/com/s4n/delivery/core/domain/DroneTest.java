package com.s4n.delivery.core.domain;


import com.s4n.delivery.core.util.ConstantUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

class DroneTest {

    @Test
    void givenADroneWithExceededAmountOfDeliveryRoutes_whenDeliver_thenOnlyMaxDeliveriesAreProcessed() {
        final List<DeliveryRoute> deliveryRoutes = Arrays.asList(
                buildDeliveryRoute(),
                buildDeliveryRoute(),
                buildDeliveryRoute(),
                buildDeliveryRoute());
        final Drone drone = new Drone("01", deliveryRoutes);

        final List<Optional<Coordinate>> deliveryCoordinates = drone.deliver();

        assertThat(deliveryCoordinates, notNullValue());
        assertThat(deliveryCoordinates, hasSize(ConstantUtil.MAX_DELIVERIES_BY_DRONE));
    }

    private DeliveryRoute buildDeliveryRoute() {
        return new DeliveryRoute("AAAAIAA");
    }
}