import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    @ParameterizedTest
    @ValueSource(strings = { "USA", "GERMANY", "BRAZIL"})
    void testWithEnumSource(Country country) {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String preferences = "Welcome";
        String expected = localizationService.locale(country);
        Assertions.assertEquals(expected, preferences);
    }
}
