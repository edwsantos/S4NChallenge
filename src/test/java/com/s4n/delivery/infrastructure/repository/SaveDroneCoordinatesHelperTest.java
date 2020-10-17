package com.s4n.delivery.infrastructure.repository;

import com.s4n.delivery.core.domain.CardinalDirection;
import com.s4n.delivery.core.domain.Coordinate;
import com.s4n.delivery.core.util.MessageUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SaveDroneCoordinatesHelperTest {

    private static final String DRONE_ID = "98";
    private static final String DRONE_REPORT_FILE_PATH = buildFilePathByDroneId();

    private static SaveDroneCoordinatesHelper saveDroneCoordinatesHelper;

    @BeforeAll
    static void setUp() {
        saveDroneCoordinatesHelper = new SaveDroneCoordinatesHelper();
    }

    @Test
    void givenADroneWithCoordinates_whenSaveDroneCoordinates_thenFileIsCreated() {
        saveDroneCoordinatesHelper.saveDroneCoordinates(DRONE_ID, buildCoordinatesList());

        final File file = new File(DRONE_REPORT_FILE_PATH);
        assertThat(file.exists(), is(true));
    }

    @Test
    void givenADroneWithCoordinates_whenSaveDroneCoordinates_thenFileContentIsCorrect() throws IOException {
        final List<Optional<Coordinate>> coordinatesList = buildCoordinatesList();

        saveDroneCoordinatesHelper.saveDroneCoordinates(DRONE_ID, coordinatesList);

        final List<String> fileLines = Files.readAllLines(Paths.get(DRONE_REPORT_FILE_PATH));

        assertThat(fileLines, notNullValue());
        assertThat(fileLines, not(empty()));
        assertThat(fileLines, hasItem(MessageUtil.DRONE_DELIVERY_REPORT_TITLE_TEMPLATE));

        coordinatesList.stream()
                .forEach(c -> {
                    final Coordinate coordinate = c.get();
                    assertThat(fileLines, hasItem(String.format(MessageUtil.DRONE_DELIVERY_REPORT_TEMPLATE, coordinate.getX(), coordinate.getY(), coordinate.getCardinalDirection().getDescription())));
                });
    }

    private List<Optional<Coordinate>> buildCoordinatesList() {
        return Arrays.asList(
                Optional.of(new Coordinate(2, 2, CardinalDirection.N)),
                Optional.of(new Coordinate(-2, 2, CardinalDirection.W)),
                Optional.of(new Coordinate(-2, -2, CardinalDirection.S))
        );

    }

    private static String buildFilePathByDroneId() {
        final String pathToResources = SaveDroneCoordinatesHelperTest.class.getClassLoader().getResource(EMPTY).getPath().substring(1);
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(pathToResources)
                .append("out")
                .append(DRONE_ID)
                .append(".txt")
                .toString();
        return fileNameBuilder.toString();
    }


    @AfterAll
    static void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(DRONE_REPORT_FILE_PATH));
    }
}