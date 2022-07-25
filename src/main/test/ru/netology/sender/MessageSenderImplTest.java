package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @Test
    void sendWhenLocationIsRu() {
        GeoService gsm = Mockito.mock(GeoService.class);
        Mockito.when(gsm.byIp("172.0.32.11")).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSenderImpl msi = new MessageSenderImpl(gsm, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");

        String expectedTextRu = "Добро пожаловать";
        String text = msi.send(headers);

        Assertions.assertEquals(expectedTextRu, text);
    }

    @Test
    void sendWhenLocationIsUSA() {
        GeoService gsm = Mockito.mock(GeoService.class);
        Mockito.when(gsm.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl msi = new MessageSenderImpl(gsm, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        String expectedTextUSA = "Welcome";
        String text = msi.send(headers);
        Assertions.assertEquals(expectedTextUSA, text);
    }
}