import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.Set;

class GeoServiceImplTest {

    @ParameterizedTest
    @ValueSource(strings = { "172.25.18.4", "172.0.6.8", "172.0.0.0" })
    void test_byIp_startWith172(String ip) {
        GeoService geoService = new GeoServiceImpl();
        Location preferences = new Location("Moscow", Country.RUSSIA, null, 0);
        Location expected = geoService.byIp(ip);
        Assertions.assertEquals(expected.getCountry(), preferences.getCountry());
    }
    @ParameterizedTest
    @ValueSource(strings = { "96.25.18.4", "96.0.6.8", "96.0.0.0" })
    void test_byIp_startWith96(String ip) {
        GeoService geoService = new GeoServiceImpl();
        Location preferences = new Location("New York", Country.USA, null, 0);
        Location expected = geoService.byIp(ip);
        Assertions.assertEquals(expected.getCountry(), preferences.getCountry());
    }

    @ParameterizedTest
    @ValueSource(strings = { "196.25.18.4", "127.0.6.8", "11.0.0.0" })
    void test_byIp_startAny(String ip) {
        GeoService geoService = new GeoServiceImpl();
        Location expected = geoService.byIp(ip);
        Assertions.assertEquals(expected, null);
    }

    @ParameterizedTest
    @CsvSource({
            "0.5, 0.5",
            "1.0, 1.0",
    })
    void test_byCoordinates(double latitude, double longitude) {
        GeoService geoService = new GeoServiceImpl();
        var expected = RuntimeException.class;
        Assertions.assertThrowsExactly(expected, () -> geoService.byCoordinates(latitude, longitude));
    }

}