import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;




public class TestGeoService {
    @ParameterizedTest
    @MethodSource("source")
    public void GeoServiceImplTest(String ip, Location expected) {
        GeoServiceImpl geoServiceImpl = new GeoServiceImpl();
        Location location = geoServiceImpl.byIp(ip);
        assertEquals(expected.getCity(), location.getCity());
        assertEquals(expected.getCountry(), location.getCountry());
        assertEquals(expected.getStreet(), location.getStreet());
        assertEquals(expected.getBuilding(), location.getBuilding());

    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)));
    }

    @ParameterizedTest
    @MethodSource("source2")
    public void localizationTest(Country country, String hi) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(country);
        assertEquals(hi, result);

    }

    private static Stream<Arguments> source2() {
        return Stream.of(Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome"));
    }
}