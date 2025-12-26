package ui;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Clean;

import java.io.File;

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
    public void shouldSubmitPracticeFormWhenAllFieldsAreValid() {
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

        $("#uploadPicture").uploadFile
                (new File("src/test/resources/img/enot.png"));

        $("#currentAddress").setValue(currentAddress);

        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        $("#submit").click();

        $("div[class='table-responsive']").shouldHave(
                text(firstName), text(lastName),
                text(userEmail),
                text("Male"),
                text(userNumber),
                text(date), text(month), text(year),
                text("Sports, Reading, Music"),
                text(picture),
                text(currentAddress),
                text("NCR Delhi"),
                text("English")
        );

        $("#closeLargeModal").click();

        $(".text-center").shouldBe(text("Practice Form"));
    }
}
