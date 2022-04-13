package com.depe.roaddifficulties;

import com.depe.roaddifficulties.exceptions.WrongParamException;
import com.depe.roaddifficulties.model.GddkiaApiClient;
import com.depe.roaddifficulties.model.Road;
import com.depe.roaddifficulties.model.TrafficDifficulty;
import com.depe.roaddifficulties.model.Voivodeship;
import com.depe.roaddifficulties.service.TrafficDifficultiesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TrafficDifficultyServiceTest {

    private GddkiaApiClient client;
    private TrafficDifficultiesService underTest;
    private String testText;

    @BeforeEach
    void setUp() {
        File file = new File("test.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            br.lines().forEach(builder::append);
            testText = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        client = mock(GddkiaApiClient.class);
        MockitoAnnotations.openMocks(this);
        underTest = new TrafficDifficultiesService(client);
    }


    private static Stream<Arguments> provideVoivodeships() {
        return Stream.of(
                Arguments.of("lubelskie", "lubelskie"),
                Arguments.of("lodzkie", "łódzkie"),
                Arguments.of("mazowieckie", "mazowieckie"),
                Arguments.of("malopolskie", "małopolskie"),
                Arguments.of("podkarpackie", "podkarpackie"),
                Arguments.of("slaskie", "śląskie"),
                Arguments.of("wielkopolskie", "wielkopolskie"),
                Arguments.of("warminsko_mazurskie", "warmińsko-mazurskie"),
                Arguments.of("kujawsko_pomorskie", "kujawsko-pomorskie"),
                Arguments.of("lubuskie", "lubuskie"),
                Arguments.of("swietokrzyskie", "świętokrzyskie"),
                Arguments.of("opolskie", "opolskie"),
                Arguments.of("pomorskie", "pomorskie"),
                Arguments.of("zachodniopomorskie", "zachodniopomorskie"),
                Arguments.of("podlaskie", "podlaskie"),
                Arguments.of("dolnoslaskie", "dolnośląskie")
        );
    }

    @ParameterizedTest(name = "{0} should be {1}")
    @MethodSource("provideVoivodeships")
    void shouldReturnResultsWithVoivodeship(String voivodeship, String expected) {
        //when
        when(client.getResponse()).thenReturn(testText);
        var result = underTest.getResultsByVoivodeship(voivodeship);
        Set<String> results = result.getTrafficDifficulties().stream().map(TrafficDifficulty::getVoivodeship)
                .map(Voivodeship::getName)
                .collect(Collectors.toSet());
        //then
        assertThat(results).hasSize(1).containsExactly(expected);
    }

    private static Stream<Arguments> provideWrongVoivodeships() {
        return Stream.of(
                Arguments.of("lubelkie"),
                Arguments.of("lo"),
                Arguments.of("lubelsk")
        );
    }

    @ParameterizedTest()
    @MethodSource("provideWrongVoivodeships")
    void shouldThrow(String voivodeship) {
        var result = assertThrows(WrongParamException.class, () -> underTest.getResultsByVoivodeship(voivodeship));
        assertThat(result.getMessage()).isEqualTo(voivodeship + " is not a voivodeship");
    }

    private static Stream<Arguments> provideRoads() {
        return Stream.of(
                Arguments.of("a2", 2),
                Arguments.of("a1", 4),
                Arguments.of("747", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRoads")
    void shouldReturnResultsWithRoad(String roadName, int amount) {
        //when
        when(client.getResponse()).thenReturn(testText);
        var result = underTest.getResultsByRoadName(roadName);
        Set<Road> results = result.getTrafficDifficulties().stream().map(TrafficDifficulty::getRoad).collect(Collectors.toSet());
        //then
        assertThat(results).hasSize(amount);
    }

    private static Stream<Arguments> provideDistance() {
        return Stream.of(
                Arguments.of(52.769575, 18.848718, 300.0, 231),
                Arguments.of(52.769575, 18.848718, 200.0, 91),
                Arguments.of(52.769575, 18.848718, 100.0, 19),
                Arguments.of(52.769575, 18.848718, 50.0, 4),
                Arguments.of(52.769575, 18.848718, 25.0, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDistance")
    void getResultsByDistance(Double latitude, Double longitude, Double distance, int size) {
        //when
        when(client.getResponse()).thenReturn(testText);
        var result = underTest.getResultsByDistance(latitude, longitude, distance);
        var results = result.getTrafficDifficulties();
        //then
        assertThat(results).hasSize(size);
    }
}
