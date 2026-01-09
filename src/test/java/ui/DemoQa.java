package ui;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoQa {

    String firstName = "Vova";
    String lastName = "Ivanov";
    String userEmail = "Test@mail.ru";
    String userNumber = "8000000000";
    String subjects = "English";
    String currentAddress = "Test Test!";
    String picture = "enot.png";
    String month = "September";
    String year = "1995";
    String date = "9";

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
    }

    @Test
    public void shouldSubmitPracticeFormWhenAllFieldsAreValidTest() {
        open("/automation-practice-form");
        Clean.cleanBanner();
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);

        $("#userEmail").setValue(userEmail);

        $("#genterWrapper").$(byText("Male")).click();

        $("#userNumber").setValue(userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").$(byText(month)).click();
        $(".react-datepicker__year-select").$(byText(year)).click();
        $("[aria-label='Next Month']").click();
        $("[aria-label='Previous Month']").click();
        $(".react-datepicker__month").$(byText(date)).click();

        $("#subjectsInput").setValue(subjects).pressEnter();

        $("label[for=hobbies-checkbox-1]").click();
        $("label[for=hobbies-checkbox-2]").click();
        $("label[for=hobbies-checkbox-3]").click();

        $("#uploadPicture").uploadFromClasspath("img/enot.png");

        $("#currentAddress").setValue(currentAddress);

        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        $("#submit").click();

        $(".modal-body").shouldHave(text(firstName));
        $(".modal-body").shouldHave(text(lastName));
        $(".modal-body").shouldHave(text(userEmail));
        $(".modal-body").shouldHave(text("Male"));
        $(".modal-body").shouldHave(text(date));
        $(".modal-body").shouldHave(text(month));
        $(".modal-body").shouldHave(text(year));
        $(".modal-body").shouldHave(text("Sports, Reading, Music"));
        $(".modal-body").shouldHave(text("Sports, Reading, Music"));
        $(".modal-body").shouldHave(text(picture));
        $(".modal-body").shouldHave(text(currentAddress));
        $(".modal-body").shouldHave(text("NCR Delhi"));
        $(".modal-body").shouldHave(text("English"));

        $("#closeLargeModal").click();

        $(".text-center").shouldBe(text("Practice Form"));
    }
}
