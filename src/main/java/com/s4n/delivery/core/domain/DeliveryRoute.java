package com.s4n.delivery.core.domain;

import com.s4n.delivery.core.util.ConstantUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class DeliveryRoute {
    private final String routeCode;

    public Optional<Coordinate> getDeliveryCoordinate() {
        if (hasRouteCode() && isRouteCodeCorrect()) {
            return Optional.of(transformRouteCodeToCoordinate());
        }
        return Optional.empty();
    }

    private boolean hasRouteCode() {
        return StringUtils.isNotEmpty(getRouteCode());
    }

    private boolean isRouteCodeCorrect() {
        return getRouteCode().matches(ConstantUtil.CORRECT_ROUTE_REGEX);
    }

    private Coordinate transformRouteCodeToCoordinate() {
        Coordinate coordinate = new Coordinate();
        for (char c : getRouteCode().toCharArray()) {
            coordinate = applyMovementToCoordinate(coordinate, c);
        }
        return coordinate;
    }

    private Coordinate applyMovementToCoordinate(Coordinate coordinate, char c) {
        switch (c) {
            case 'A':
                if (CardinalDirection.N.equals(coordinate.getCardinalDirection())) {
                    coordinate = coordinate.withY(coordinate.getY() + 1);
                } else if (CardinalDirection.S.equals(coordinate.getCardinalDirection())) {
                    coordinate = coordinate.withY(coordinate.getY() - 1);
                } else if (CardinalDirection.E.equals(coordinate.getCardinalDirection())) {
                    coordinate = coordinate.withX(coordinate.getX() + 1);
                } else if (CardinalDirection.W.equals(coordinate.getCardinalDirection())) {
                    coordinate = coordinate.withX(coordinate.getX() - 1);
                }
                break;
            case 'I':
                if (CardinalDirection.N.equals(coordinate.getCardinalDirection())) {
                    coordinate = coordinate.withCardinalDirection(CardinalDirection.W);
                } else if (CardinalDirection.S.equals(coordinate.getCardinalDirection())) {
                    coordinate = coordinate.withCardinalDirection(CardinalDirection.E);
                } else if (CardinalDirection.E.equals(coordinate.getCardinalDirection())) {
                    coordinate = coordinate.withCardinalDirection(CardinalDirection.N);
                } else if (CardinalDirection.W.equals(coordinate.getCardinalDirection())) {
                    coordinate = coordinate.withCardinalDirection(CardinalDirection.S);
                }
                break;
            case 'D':
                if (CardinalDirection.N.equals(coordinate.getCardinalDirection())) {
                    coordinate = coordinate.withCardinalDirection(CardinalDirection.E);
                } else if (CardinalDirection.S.equals(coordinate.getCardinalDirection())) {
                    coordinate = coordinate.withCardinalDirection(CardinalDirection.W);
                } else if (CardinalDirection.E.equals(coordinate.getCardinalDirection())) {
                    coordinate = coordinate.withCardinalDirection(CardinalDirection.S);
                } else if (CardinalDirection.W.equals(coordinate.getCardinalDirection())) {
                    coordinate = coordinate.withCardinalDirection(CardinalDirection.N);
                }
                break;
        }
        return coordinate;
    }
}
