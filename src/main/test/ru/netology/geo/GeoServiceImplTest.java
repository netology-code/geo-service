package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void byIp() {
        GeoServiceImpl gsi = new GeoServiceImpl();
        Location location1 = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location location2 = new Location(null, null, null, 0);
        Location location3 = new Location("New York", Country.USA, " 10th Avenue", 32);
        Assertions.assertEquals(location1, gsi.byIp("172.0.32.11"));
        Assertions.assertEquals(location2, gsi.byIp("127.0.0.1"));
        Assertions.assertEquals(location3, gsi.byIp("96.44.183.149"));
    }

}