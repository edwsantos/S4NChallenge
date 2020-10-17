package com.s4n.delivery.infrastructure.repository;

import com.s4n.delivery.core.domain.DeliveryRoute;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class FindRoutesInFileHelperTest {

    private static FindRoutesInFileHelper findRoutesInFileHelper;

    @BeforeAll
    static void setUp() {
        findRoutesInFileHelper = new FindRoutesInFileHelper();
    }

    @Test
    void givenADroneWithFile_whenFindDeliveryRoutesByDrone_thenDeliveryRoutesAreReturned() {
        final List<DeliveryRoute> routes = findRoutesInFileHelper.findRoutesByDrone("01");

        assertThat(routes, notNullValue());
        assertThat(routes, is(not(empty())));
    }

    @Test
    void givenADroneWithoutFile_whenFindDeliveryRoutesByDrone_thenDeliveryRoutesAreEmpty() {
        final List<DeliveryRoute> routes = findRoutesInFileHelper.findRoutesByDrone("99");

        assertThat(routes, notNullValue());
        assertThat(routes, is(empty()));
    }

    @Test
    void givenADroneWithEmptyFile_whenFindDeliveryRoutesByDrone_thenDeliveryRoutesAreEmpty() {
        final List<DeliveryRoute> routes = findRoutesInFileHelper.findRoutesByDrone("02");

        assertThat(routes, notNullValue());
        assertThat(routes, is(empty()));
    }
}