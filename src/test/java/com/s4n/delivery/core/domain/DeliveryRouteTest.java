package com.s4n.delivery.core.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class DeliveryRouteTest {

    private DeliveryRoute deliveryRoute;


    @Test
    void givenAnEmptyRouteCode_whenGetDeliveryCoordinate_thenOptionalEmptyIsReturned() {
        deliveryRoute = new DeliveryRoute(EMPTY);

        final Optional<Coordinate> deliveryCoordinate = deliveryRoute.getDeliveryCoordinate();

        assertThat(deliveryCoordinate, is(Optional.empty()));
    }

    @Test
    void givenAnIncorrectRouteCode_whenGetDeliveryCoordinate_thenOptionalEmptyIsReturned() {
        deliveryRoute = new DeliveryRoute("ASDASD");

        final Optional<Coordinate> deliveryCoordinate = deliveryRoute.getDeliveryCoordinate();

        assertThat(deliveryCoordinate, is(Optional.empty()));
    }

    @Test
    void givenACorrectRouteCode_whenGetDeliveryCoordinate_thenACoordinateIsReturned() {
        deliveryRoute = new DeliveryRoute("AAAAIAA");

        final Optional<Coordinate> deliveryCoordinate = deliveryRoute.getDeliveryCoordinate();

        assertThat(deliveryCoordinate, not(Optional.empty()));
        assertThat(deliveryCoordinate.get(), instanceOf(Coordinate.class));
    }

    @ParameterizedTest
    @CsvSource({"AA,0,2,N",
            "AAIA,-1,2,W",
            "AADA,1,2,E",
            "DDAAIA,1,-2,E",
            "DDAAA,0,-3,S"
    })
    void givenACorrectRouteCode_whenGetDeliveryCoordinate_thenTheReturnedCoordinateIsCorrect(
            final String routeCode,
            final int expectedX,
            final int expectedY,
            final CardinalDirection expectedCardinalDirection) {
        deliveryRoute = new DeliveryRoute(routeCode);

        final Optional<Coordinate> deliveryCoordinate = deliveryRoute.getDeliveryCoordinate();

        assertThat(deliveryCoordinate, notNullValue());
        assertThat(deliveryCoordinate, not(Optional.empty()));
        assertThat(deliveryCoordinate.get().getX(), is(expectedX));
        assertThat(deliveryCoordinate.get().getY(), is(expectedY));
        assertThat(deliveryCoordinate.get().getCardinalDirection(), is(expectedCardinalDirection));
    }
}