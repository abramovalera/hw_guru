package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private final SelenideElement dateInput = $("#dateOfBirthInput");

    /**
     * Устанавливает дату рождения.
     *
     * @param day день (например, "15")
     * @param month месяц (например, "May")
     * @param year год (например, "1995")
     */
    public void setDate(String day, String month, String year) {
        openDatePicker();
        selectMonth(month);
        selectYear(year);
        selectDay(day);
    }

    private void openDatePicker() {
        dateInput.click();
    }

    private void selectMonth(String month) {
        $(".react-datepicker__month-select").selectOption(month);
    }

    private void selectYear(String year) {
        $(".react-datepicker__year-select").selectOption(year);
    }

    private void selectDay(String day) {
        String formattedDay = formatDay(day);
        $(".react-datepicker__day--0" + formattedDay)
                .shouldBe(visible)
                .click();
    }

    private String formatDay(String day) {
        return day.length() == 1 ? "0" + day : day;
    }
}
