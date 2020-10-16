package com.s4n.delivery.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;

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
}
