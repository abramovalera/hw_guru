package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {
    private final SelenideElement tableResponsiveInput = $(".table-responsive");
    private final SelenideElement closeLargeModalInput = $("#closeLargeModal");

    /**
     * Проверяет одну строчку в таблице по ключу и значению
     */
    public ResultsTableComponent checkResult(String key, String value) {
        tableResponsiveInput
                .$(byText(key))
                .parent()  //поднимаемся к <tr>
                .shouldHave(text(value));
        return this;
    }

    public void closeLargeModal() {
        closeLargeModalInput.click();
    }
}
