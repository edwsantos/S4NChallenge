package com.s4n.delivery.infrastructure.repository;

import com.s4n.delivery.core.domain.Coordinate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static com.s4n.delivery.core.util.MessageUtil.DRONE_DELIVERY_REPORT_TEMPLATE;
import static com.s4n.delivery.core.util.MessageUtil.DRONE_DELIVERY_REPORT_TITLE_TEMPLATE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class SaveDroneCoordinatesHelper {

    public void saveDroneCoordinates(final String droneId, final List<Optional<Coordinate>> coordinates) {
        deleteReportFileIfExists(droneId);
        saveDroneCoordinatesToFile(droneId, coordinates);
    }

    private void deleteReportFileIfExists(final String droneId) {
        try {
            Files.deleteIfExists(Paths.get(buildFilePathByDroneId(droneId)));
        } catch (IOException e) {
        }
    }


    private String buildFilePathByDroneId(final String droneId) {
        final String pathToResources = getClass().getClassLoader().getResource(EMPTY).getPath().substring(1);
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(pathToResources)
                .append("out")
                .append(droneId)
                .append(".txt")
                .toString();
        return fileNameBuilder.toString();
    }

    private void saveDroneCoordinatesToFile(final String droneId, final List<Optional<Coordinate>> coordinates) {
        final String reportContent = buildFileContent(coordinates);
        writeContentToFile(droneId, reportContent);
    }

    private String buildFileContent(final List<Optional<Coordinate>> coordinates) {
        final String newLine = System.getProperty("line.separator");
        final StringBuilder reportContent = new StringBuilder();

        reportContent.append(DRONE_DELIVERY_REPORT_TITLE_TEMPLATE).append(newLine);
        coordinates.stream()
                .forEach(coordinate -> {
                    coordinate.ifPresent(c -> {
                        reportContent.append(String.format(DRONE_DELIVERY_REPORT_TEMPLATE, c.getX(), c.getY(), c.getCardinalDirection().getDescription()));
                    });
                    reportContent.append(newLine);
                });
        return reportContent.toString();
    }

    private void writeContentToFile(final String droneId, final String reportContent) {
        try {
            Files.write(Paths.get(buildFilePathByDroneId(droneId)), reportContent.getBytes());
        } catch (IOException e) {
        }
    }
}
