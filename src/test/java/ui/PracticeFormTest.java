package ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PracticeFormTest extends TestBase {

    @DisplayName("Форма отправляется успешно, когда все поля заполнены корректно")
    @Test
    public void shouldSubmitPracticeFormWhenAllFieldsAreValidTest() {
        practiceFormPage
                .openPage("/automation-practice-form")
                .removeBanner()
                .setFirstName("Vova")
                .setLastName("Ivanov")
                .setEmail("Test@mail.ru")
                .setGender("Male")
                .setUserNumber("8000000000");
        calendarComponent
                .setData("5", "May", "1995");
        practiceFormPage
                .setSubjects("e")
                .setHobbies("Sports", "Reading")
                .uploadPicture("enot")
                .setCurrentAddress("ул. Донская, д. 8, стр. 1, 119049")
                .setStateAndCity("NCR", "Delhi")
                .submit();
        resultsTableComponent
                .checkResult("Student Name", "Vova" + " " + "Ivanov")
                .checkResult("Student Email", "Test@mail.ru")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "8000000000")
                .checkResult("Date of Birth", "5" + " " + "May" + "," + "1995")
                .checkResult("Subjects", "e")
                .checkResult("Hobbies", "Sports" + ", " + "Reading")
                .checkResult("Picture", "enot")
                .checkResult("Address", "ул. Донская, д. 8, стр. 1, 119049")
                .checkResult("State and City", "NCR" + " " + "Delhi")
                .closeLargeModal();
        practiceFormPage
                .checkPageHeader();
    }

    @DisplayName("Форма отправляется успешно, когда заполнены только обязательные поля")
    @Test
    public void shouldSubmitPracticeFormWhenOnlyRequiredFieldsFilled() {
        practiceFormPage
                .openPage("/automation-practice-form")
                .removeBanner()
                .setFirstName("Vova")
                .setLastName("Ivanov")
                .setGender("Male")
                .setUserNumber("8000000000")
                .submit();
        resultsTableComponent
                .checkResult("Student Name", "Vova" + " " + "Ivanov")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "8000000000")
                .closeLargeModal();
        practiceFormPage
                .checkPageHeader();
    }

    @DisplayName("Форма не отправляется, когда не заполнено обязательное поле Name")
    @Test
    public void shouldNotSubmitFormWhenRequiredFieldsMissing() {
        practiceFormPage
                .openPage("/automation-practice-form")
                .removeBanner()
                .setGender("Male")
                .setUserNumber("8000000000")
                .submit();
        resultsTableComponent
                .tableResponsive().shouldNotBe();
    }
}
