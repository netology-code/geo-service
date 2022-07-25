package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    void localeCheck() {
        LocalizationServiceImpl lsi = new LocalizationServiceImpl();
        Assertions.assertEquals(lsi.locale(Country.RUSSIA), "Добро пожаловать");
        Assertions.assertEquals(lsi.locale(Country.USA), "Welcome");
    }

}