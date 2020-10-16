package com.s4n.delivery.core.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class CoordinateTest {

    private static final int ZERO = 0;

    @Test
    void givenANoArgsConstructor_whenInvoked_thenDefaultValuesAreSet() {
        final Coordinate coordinate = new Coordinate();

        assertThat(coordinate, notNullValue());
        assertThat(coordinate.getX(), is(ZERO));
        assertThat(coordinate.getY(), is(ZERO));
        assertThat(coordinate.getCardinalDirection(), is(CardinalDirection.N));
    }
}