package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {
    private LocalizationService localizationService = new LocalizationServiceImpl();

    @ParameterizedTest
    @EnumSource(Country.class)
    void locale(Country country) {
        String locale = localizationService.locale(country);

        if (country == Country.RUSSIA) {
            Assertions.assertEquals("Добро пожаловать", locale);
        } else {
            Assertions.assertEquals("Welcome", locale);
        }
    }
}