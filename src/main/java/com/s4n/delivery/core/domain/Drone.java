package com.s4n.delivery.core.domain;

import com.s4n.delivery.core.util.ConstantUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Getter
@RequiredArgsConstructor
public class Drone {
    private final String id;
    private final List<DeliveryRoute> routes;

    public List<Optional<Coordinate>> deliver() {
        return this.routes.stream()
                .limit(ConstantUtil.MAX_DELIVERIES_BY_DRONE)
                .map(DeliveryRoute::getDeliveryCoordinate)
                .collect(Collectors.toList());
    }
}
