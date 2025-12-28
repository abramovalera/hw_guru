package pages.components;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private final SelenideElement dateInput = $("#dateOfBirthInput");

    /**
     * Устанавливает дату
     *
     * @param day   День
     * @param month Месяц (например: May)
     * @param year  Год
     */
    public void setData(String day, String month, String year) {
        dateInput.click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        String resultDate = day.length() == 1 ? "0" + day : day;
        $(".react-datepicker__day--0" + resultDate).click();
    }
}
