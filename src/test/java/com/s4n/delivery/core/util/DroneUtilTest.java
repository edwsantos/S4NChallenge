package com.s4n.delivery.core.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DroneUtilTest {

    @ParameterizedTest
    @CsvSource({
            "1,01",
            "5,05",
            "10,10"
    })
    void givenANumber_whenGetDroneIdNyNumber_then2DigitsStringIsReturned(final int number, final String expectedString) {
        final String droneId = DroneUtil.getDroneIdByNumber(number);

        assertThat(droneId, is(expectedString));
    }
}