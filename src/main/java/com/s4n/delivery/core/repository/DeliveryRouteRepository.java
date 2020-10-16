package com.s4n.delivery.core.repository;

import com.s4n.delivery.core.domain.Coordinate;
import com.s4n.delivery.core.domain.DeliveryRoute;

import java.util.List;
import java.util.Optional;

public interface DeliveryRouteRepository {
    List<DeliveryRoute> findDeliveryRoutesByDrone(String droneId);

    void saveDeliveryRoutesCoordinates(String droneId, List<Optional<Coordinate>> coordinates);
}
