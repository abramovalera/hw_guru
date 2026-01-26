package ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.PracticeFormPage;
import pages.components.CalendarComponent;
import pages.components.ResultsTableComponent;

import java.util.stream.Stream;

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
                .setSubjects(testData.subjectLetter)
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


    @ParameterizedTest(name = "Форма успешно отправляется с пользователем {0} {1}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    public void shouldSubmitFormWithDataFromCsvFile(String firstName, String lastName,
                                                    String email, String phone, String gender) {
        practiceFormPage
                .openPage("/automation-practice-form")
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(phone)
                .submit();
        resultsTableComponent
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phone)
                .closeLargeModal();
        practiceFormPage
                .checkPageHeader();
    }

    @ParameterizedTest(name = "Форма успешно отправляется с гендером {0} для пользователя {1} {2}")
    @CsvSource({
            "Male, Ivan, Petrov, 1234567890",
            "Female, Anna, Sidorova, 9876543210",
            "Other, Alex, Johnson, 5555555555"
    })
    public void shouldSubmitFormWithDifferentGendersFromCsvSource(String gender, String firstName,
                                                                  String lastName, String phone) {
        practiceFormPage
                .openPage("/automation-practice-form")
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phone)
                .submit();
        resultsTableComponent
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phone)
                .closeLargeModal();
        practiceFormPage
                .checkPageHeader();
    }

    @ParameterizedTest(name = "Форма успешно отправляется с телефоном {0}")
    @ValueSource(strings = {"1234567890", "9876543210", "5555555555", "1111111111", "9999999999"})
    public void shouldSubmitFormWithDifferentPhoneNumbersFromValueSource(String phone) {
        practiceFormPage
                .openPage("/automation-practice-form")
                .removeBanner()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setUserNumber(phone)
                .submit();
        resultsTableComponent
                .checkResult("Student Name", testData.getFullName())
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", phone)
                .closeLargeModal();
        practiceFormPage
                .checkPageHeader();
    }

    static Stream<Arguments> provideTestNames() {
        return Stream.of(
                Arguments.of("John", "Doe"),
                Arguments.of("Jane", "Smith"),
                Arguments.of("Bob", "Wilson")
        );
    }

    @ParameterizedTest(name = "Форма успешно отправляется с именем {0} {1}")
    @MethodSource("provideTestNames")
    public void shouldSubmitFormWithDifferentNamesFromMethodSource(String firstName, String lastName) {
        practiceFormPage
                .openPage("/automation-practice-form")
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(testData.gender)
                .setUserNumber(testData.phone)
                .submit();
        resultsTableComponent
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.phone)
                .closeLargeModal();
        practiceFormPage
                .checkPageHeader();
    }

    @ParameterizedTest(name = "Форма успешно отправляется с гендером {0}")
    @EnumSource(Gender.class)
    public void shouldSubmitFormWithDifferentGendersFromEnum(Gender gender) {
        practiceFormPage
                .openPage("/automation-practice-form")
                .removeBanner()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(gender.getDisplayName())
                .setUserNumber(testData.phone)
                .submit();
        resultsTableComponent
                .checkResult("Student Name", testData.getFullName())
                .checkResult("Gender", gender.getDisplayName())
                .checkResult("Mobile", testData.phone)
                .closeLargeModal();
        practiceFormPage
                .checkPageHeader();
    }
}
