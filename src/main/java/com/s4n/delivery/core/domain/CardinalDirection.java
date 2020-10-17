package com.s4n.delivery.core.domain;

import static com.s4n.delivery.core.util.MessageUtil.*;

public enum CardinalDirection {
    N(NORTH), S(SOUTH), E(EAST), W(WEST);

    private String description;

    CardinalDirection(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
