package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {
    private GeoService geoServiceMock = Mockito.mock();
    private LocalizationService localizationService = Mockito.mock();
    private MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationService);

    @ParameterizedTest
    @EnumSource(Country.class)
    @NullSource
    public void sendTest(Country country) {
        Map<String, String> headers = new HashMap<>();
        String expectedMessage = country == Country.RUSSIA ?
                "Добро пожаловать" : "Welcome";

        if (country != null) {
            String ip = "127.0.0.1";
            headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

            Location locationMock = Mockito.mock(Location.class);
            Mockito.when(geoServiceMock.byIp(ip)).thenReturn(locationMock);
            Mockito.when(locationMock.getCountry()).thenReturn(country);

            Mockito.when(localizationService.locale(country)).thenReturn(expectedMessage);
        } else {
            Mockito.when(localizationService.locale(Country.USA)).thenReturn(expectedMessage);
        }

        String message = messageSender.send(headers);

        Assertions.assertEquals(expectedMessage, message);
    }
}