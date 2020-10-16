package com.s4n.delivery.infrastructure.repository;

import com.s4n.delivery.core.domain.Coordinate;
import com.s4n.delivery.core.domain.DeliveryRoute;
import com.s4n.delivery.core.repository.DeliveryRouteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryRouteRepositoryImpl implements DeliveryRouteRepository {

    @Override
    public List<DeliveryRoute> findDeliveryRoutesByDrone(String droneId) {
        return new ArrayList<>();
    }

    @Override
    public void saveDeliveryRoutesCoordinates(String droneId, List<Optional<Coordinate>> coordinates) {

    }
}
