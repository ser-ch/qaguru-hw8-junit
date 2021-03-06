package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class E2e4Page {
    SelenideElement search = $("#search"),
            currentCityElement = $("span.currentCity");

    @Getter
    ElementsCollection resultList = $$("#view_list");

    public E2e4Page openPage() {
        open("https://novosibirsk.e2e4online.ru");
        return this;
    }

    public E2e4Page setSearchField(String searchItem) {
        search.setValue(searchItem);
        return this;
    }

    public void clickSearchButton() {
        $("button.search-button").click();
    }

    public E2e4Page setCity(String city) {
        currentCityElement.click();
        SelenideElement cityElement = $$(".city").findBy(text(city));
        cityElement.scrollIntoView(false).click();
        return this;
    }

    public void checkCurrentCity(String currentCity) {
        currentCityElement.scrollTo().shouldHave(text(currentCity));
    }

    public void checkExpectedResults(String expectedInResults) {
        resultList.findBy(Condition.text(expectedInResults)).shouldHave(Condition.text(expectedInResults));
    }
}
