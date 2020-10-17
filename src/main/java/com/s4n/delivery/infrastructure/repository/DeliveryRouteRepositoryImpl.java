package com.s4n.delivery.infrastructure.repository;

import com.s4n.delivery.core.domain.Coordinate;
import com.s4n.delivery.core.domain.DeliveryRoute;
import com.s4n.delivery.core.repository.DeliveryRouteRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DeliveryRouteRepositoryImpl implements DeliveryRouteRepository {
    private final FindRoutesInFileHelper findRoutesInFileHelper;
    private final SaveDroneCoordinatesHelper saveDroneCoordinatesHelper;

    @Override
    public List<DeliveryRoute> findDeliveryRoutesByDrone(final String droneId) {
        return findRoutesInFileHelper.findRoutesByDrone(droneId);
    }

    @Override
    public void saveDeliveryRoutesCoordinates(final String droneId, final List<Optional<Coordinate>> coordinates) {
        saveDroneCoordinatesHelper.saveDroneCoordinates(droneId, coordinates);
    }
}
