import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;




public class TestMessageSender {
    @Test
    public void messageSenderRussianScenarioTest() {
        Map<String, String> map = new HashMap<>();
        map.put("x-real-ip", "172.0.32.11");
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Mockito.when(geoService.byIp("172.0.32.11")).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        String res = messageSender.send(map);
        assertEquals("Добро пожаловать", res);

    }

    @Test
    public void messageSenderEnglishScenarioTest() {
        Map<String, String> map = new HashMap<>();
        map.put("x-real-ip", "96.44.183.149");
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Mockito.when(geoService.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        String res = messageSender.send(map);
        assertEquals("Welcome", res);

    }
}