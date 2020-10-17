package com.s4n.delivery.infrastructure.repository;

import com.s4n.delivery.core.domain.DeliveryRoute;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindRoutesInFileHelper {

    public List<DeliveryRoute> findRoutesByDrone(final String droneId) {
        final URL routesFileName = buildFilePathByDroneId(droneId);

        return Optional.ofNullable(routesFileName)
                .map(this::readDeliveryRoutesFromFile)
                .orElseGet(ArrayList::new);
    }

    private URL buildFilePathByDroneId(final String droneId) {
        final StringBuilder sb = new StringBuilder();
        final String fileName = sb.append("in")
                .append(droneId)
                .append(".txt")
                .toString();
        return getClass().getClassLoader().getResource(fileName);
    }

    private List<DeliveryRoute> readDeliveryRoutesFromFile(URL url) {
        try (Stream<String> stream = Files.lines(Paths.get(url.toURI()))) {
            return stream
                    .map(DeliveryRoute::new)
                    .collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            return new ArrayList<>();
        }
    }
}
