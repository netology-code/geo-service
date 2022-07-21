package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @Test
    void send() {
        GeoServiceMock gsmRu = new GeoServiceMock();
        GeoServiceMock gsmUSA = new GeoServiceMock();
        LocalizationServiceMock lsm = new LocalizationServiceMock();

        gsmRu.setLocation(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        gsmUSA.setLocation(new Location("New York", Country.USA, " 10th Avenue", 32));

        Assertions.assertEquals("Отправлено сообщение: Добро пожаловать", "Отправлено сообщение: "
                + lsm.locale(gsmRu.byIp("").getCountry()));
        Assertions.assertEquals("Отправлено сообщение: Welcome", "Отправлено сообщение: "
                + lsm.locale(gsmUSA.byIp("").getCountry()));

    }
}