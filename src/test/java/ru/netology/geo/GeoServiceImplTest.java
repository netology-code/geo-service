package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.lang.reflect.Executable;
import java.util.stream.Stream;

class GeoServiceImplTest {

    GeoServiceImpl geoService = new GeoServiceImpl();


    @ParameterizedTest
    @MethodSource("arguments")
    void byIp(String ip, Location expectedLocation) {
        Location result = geoService.byIp(ip);
        Assertions.assertEquals(expectedLocation, result);
    }

    @Test
    void byCoordinates() {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> {
            geoService.byCoordinates(23.05, 36.34);
        });
        Assertions.assertEquals("Not implemented", runtimeException.getMessage());
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.arguments("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.arguments("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.arguments("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.arguments("172.0.31.12", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.arguments("96.45.183.149", new Location("New York", Country.USA, null,  0)),
                Arguments.arguments("128.0.0.1", null)
        );
    }
}