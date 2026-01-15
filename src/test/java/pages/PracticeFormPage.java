package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    protected final ElementsCollection
            gendersInput = $$("#genterWrapper label.custom-control-label"),
            hobbiesInput = $$("#hobbiesWrapper label.custom-control-label");

    protected final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            numberInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitInput = $("#submit"),
            herderInput = $(".text-center"),
            resultModalTitle = $("#example-modal-sizes-title-lg"),
            subjectMenuInput = $(".subjects-auto-complete__menu");

    public PracticeFormPage openPage(String url) {
        open(url);
        return this;
    }

    public PracticeFormPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    /**
     * Устанавливает пол на форме
     *
     * @param gender название пола для выбора (например, "Male", "Female", "Other")
     * @return текущий экземпляр PracticeFormPage
     */
    public PracticeFormPage setGender(String gender) {
        gendersInput
                .findBy(text(gender))
                .click();
        return this;
    }

    public PracticeFormPage setUserNumber(String value) {
        numberInput.setValue(value);
        return this;
    }

    /**
     * Выбирает предмет в поле "Subjects" путем ввода буквы.
     *
     * @param letter буква для ввода в поле Subjects
     * @return текущий экземпляр PracticeFormPage
     */
    public PracticeFormPage setSubjects(String letter) {
        subjectsInput.clear();
        subjectsInput.setValue(letter);
        subjectMenuInput.shouldBe(visible);
        subjectsInput.pressEnter();
        return this;
    }

    /**
     * Отмечает переданные в hobbies чек-боксы
     *
     * @param hobbies название hobbies (например: Sports)
     * @return текущий экземпляр PracticeFormPage
     */
    public PracticeFormPage setHobbies(List<String> hobbies) {
        for (String hobby : hobbies) {
            hobbiesInput
                    .findBy(text(hobby))
                    .click();
        }
        return this;
    }

    /**
     * Загрузка файла из classpath.
     *
     * @param filePath путь к файлу относительно classpath (например, "img/png.png")
     * @return текущий экземпляр PracticeFormPage
     */
    public PracticeFormPage uploadPicture(String filePath) {
        uploadPictureInput.uploadFromClasspath(filePath);
        return this;
    }

    public PracticeFormPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    /**
     * Устанавливает штат и город.
     *
     * @param state название штата
     * @param city название города
     * @return текущий экземпляр PracticeFormPage
     */
    public PracticeFormPage setStateAndCity(String state, String city) {
        stateInput.click();
        $(byText(state)).click();
        cityInput.click();
        $(byText(city)).click();
        return this;
    }

    public PracticeFormPage submit() {
        submitInput.click();
        return this;
    }

    public PracticeFormPage checkPageHeader() {
        herderInput.shouldBe(text("Practice Form"));
        return this;
    }

    public PracticeFormPage checkResultModalNotVisible() {
        resultModalTitle.shouldNot(appear);
        return this;
    }
}

