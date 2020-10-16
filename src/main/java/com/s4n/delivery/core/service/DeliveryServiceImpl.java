package com.s4n.delivery.core.service;


import com.s4n.delivery.core.domain.Coordinate;
import com.s4n.delivery.core.domain.DeliveryRoute;
import com.s4n.delivery.core.domain.Drone;
import com.s4n.delivery.core.repository.DeliveryRouteRepository;
import com.s4n.delivery.core.util.ConstantUtil;
import com.s4n.delivery.core.util.DroneUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRouteRepository deliveryRouteRepository;

    @Override
    public void deliver() {
        IntStream.rangeClosed(1, ConstantUtil.NUM_AVAILABLE_DRONES)
                .parallel()
                .forEach(id -> {
                    final String droneId = DroneUtil.getDroneIdByNumber(id);
                    final List<DeliveryRoute> deliveryRoutesByDrone =
                            deliveryRouteRepository.findDeliveryRoutesByDrone(droneId);

                    final Drone drone = new Drone(droneId, deliveryRoutesByDrone);
                    final List<Optional<Coordinate>> deliveryCoordinates = drone.deliver();

                    saveDeliveryCoordinates(droneId, deliveryCoordinates);
                });
    }

    private void saveDeliveryCoordinates(final String droneId, final List<Optional<Coordinate>> coordinates) {
        deliveryRouteRepository.saveDeliveryRoutesCoordinates(droneId, coordinates);
    }
}
