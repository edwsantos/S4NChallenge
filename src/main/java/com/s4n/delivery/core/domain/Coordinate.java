package com.s4n.delivery.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;

import static com.s4n.delivery.core.util.ConstantUtil.MAX_DELIVERY_AREA;

@With
@Getter
@AllArgsConstructor
public class Coordinate {
    int x;
    int y;
    CardinalDirection cardinalDirection;

    public Coordinate() {
        this.x = 0;
        this.y = 0;
        this.cardinalDirection = CardinalDirection.N;
    }

    public boolean isCoordinateWithinDeliveryArea() {
        return Math.abs(getX()) <= MAX_DELIVERY_AREA && Math.abs(getY()) <= MAX_DELIVERY_AREA;
    }
}
