package ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import pages.components.CalendarComponent;
import pages.components.ResultsTableComponent;

public class PracticeFormTest extends TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();
    CalendarComponent calendarComponent = new CalendarComponent();
    ResultsTableComponent resultsTableComponent = new ResultsTableComponent();

    @DisplayName("Форма отправляется успешно, когда все поля заполнены корректно")
    @Test
    public void shouldSubmitPracticeFormWhenAllFieldsAreValidTest() {
        practiceFormPage
                .openPage("/automation-practice-form")
                .removeBanner()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.email)
                .setGender(testData.gender)
                .setUserNumber(testData.phone);
        calendarComponent.setDate(testData.day, testData.month, testData.year);
        practiceFormPage
                .setSubjects(randomUtils)
                .setHobbies(testData.hobbies)
                .uploadPicture(testData.classpathFile)
                .setCurrentAddress(testData.address)
                .setStateAndCity(testData.state, testData.city)
                .submit();
        resultsTableComponent
                .checkResult("Student Name", testData.getFullName())
                .checkResult("Student Email", testData.email)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.phone)
                .checkResult("Date of Birth", testData.getFormattedDate())
                .checkResult("Subjects", testData.subject)
                .checkResult("Hobbies", testData.getFormattedHobbies())
                .checkResult("Picture", testData.expectedFileName)
                .checkResult("Address", testData.address)
                .checkResult("State and City", testData.getFormattedStateCity())
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
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setUserNumber(testData.phone)
                .submit();
        resultsTableComponent
                .checkResult("Student Name", testData.getFullName())
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.phone)
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
                .setGender(testData.gender)
                .setUserNumber(testData.phone)
                .submit()
                .checkResultModalNotVisible();
    }
}
