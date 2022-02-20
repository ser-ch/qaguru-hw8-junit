import data.CitiesEnum;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pages.E2e4Page;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;

class ParameterizedTests extends TestBase{
    E2e4Page e2e4Page = new E2e4Page();

    @ParameterizedTest(name = "Когда ищем на e2e4 \"{0}\", то в результатах должен присутствовать текст запросов")
    @ValueSource(strings = {"ноутбук", "телефон", "холодильник"})
    void parametrizedStrings(String searchItem) {
        e2e4Page.openPage().setSearchField(searchItem).clickSearchButton();
        e2e4Page.getResultList().forEach(result -> result.shouldHave(text(searchItem)));
    }

    @ParameterizedTest(name = "Когда задаём город \"{0}\", то отображается город доставки")
    @EnumSource(value = CitiesEnum.class, names = {"BISHKEK"}, mode = EnumSource.Mode.EXCLUDE)
    void parametrizedEnum(CitiesEnum city) {
        e2e4Page.openPage().setCity(city.getCity()).checkCurrentCity(city.getCity());
    }

    static Stream<Arguments> searchProductName() {
        return Stream.of(
                Arguments.of("монитор acer", "Монитор 17\" Acer V176Lb TN"),
                Arguments.of("телефон xiaomi", "Гибридная защитная пленка Red Line"),
                Arguments.of("ssd samsung", "Твердотельный накопитель (SSD) Samsung 1Tb 970 EVO Plus")
        );
    }

    @MethodSource("searchProductName")
    @ParameterizedTest(name = "{index} Когда ищем {0} ожидаем увидеть в результатах {1}")
    void parametrizedMethodSource(String searchProduct, String expectedInResults) {
        e2e4Page.openPage().setSearchField(searchProduct).clickSearchButton();
        e2e4Page.checkExpectedResults(expectedInResults);
    }

    @CsvSource(value = {
            "'монитор acer', 'Монитор 17\"Acer V176Lb TN'",
            "'телефон xiaomi', 'Гибридная защитная пленка Red Line'",
            "'ssd samsung', 'Твердотельный накопитель (SSD) Samsung 1Tb 970 EVO Plus'"
    })
    @ParameterizedTest(name = "{index} Когда ищем {0} ожидаем увидеть в результатах {1}")
    void parametrizedCsvSource(String searchProduct, String expectedInResults) {
        e2e4Page.openPage().setSearchField(searchProduct).clickSearchButton();
        e2e4Page.checkExpectedResults(expectedInResults);
    }

    @CsvFileSource(resources = "/csvTestData.csv")
    @ParameterizedTest(name = "{index} Когда ищем {0} ожидаем увидеть в результатах {1}")
    void parametrizedCsvFileSource(String searchProduct, String expectedInResults) {
        e2e4Page.openPage().setSearchField(searchProduct).clickSearchButton();
        e2e4Page.checkExpectedResults(expectedInResults);
    }

}
