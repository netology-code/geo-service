import org.junit.jupiter.api.Test;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestLocalizationServiceImpl {
    @Test
    public void LocationByCoordinatesTest() throws RuntimeException {

        Throwable thrown = assertThrows(RuntimeException.class, () -> {
            GeoServiceImpl geoServiceImpl = new GeoServiceImpl();
            geoServiceImpl.byCoordinates(3.7, 14.5);
        });

    }
}