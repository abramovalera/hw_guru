package ui;

import utils.RandomData;
import utils.RandomUtils;

import java.util.List;

public class TestData {
    public final String firstName;
    public final String lastName;
    public final String email;
    public final String phone;
    public final String address;
    public final String gender;
    public final String day;
    public final String month;
    public final String year;
    public final List<String> hobbies;
    public final String state;
    public final String city;
    public final String fileType;
    public final String classpathFile;
    public final String expectedFileName;
    public final String subjectLetter;
    public final String subject;

    public TestData(RandomData randomData, RandomUtils randomUtils) {
        this.firstName = randomData.firstName();
        this.lastName = randomData.lastName();
        this.email = randomData.email();
        this.phone = randomData.phone();
        this.address = randomData.address();
        this.gender = randomUtils.gender();
        this.day = randomUtils.randomDay();
        this.month = randomUtils.randomMonth();
        this.year = randomUtils.randomYear();
        this.hobbies = randomUtils.randomHobbies();
        this.state = randomUtils.randomState();
        this.city = randomUtils.randomCity(this.state);
        this.subjectLetter = randomUtils.randomSubjectLetter();
        this.subject = this.subjectLetter;

        this.fileType = randomUtils.randomFileType();
        if ("png".equals(this.fileType)) {
            this.classpathFile = "img/png.png";
            this.expectedFileName = "png.png";
        } else {
            this.classpathFile = "img/txt.txt";
            this.expectedFileName = "txt.txt";
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFormattedDate() {
        String formattedDay = day.length() == 1 ? "0" + day : day;
        return formattedDay + " " + month + "," + year;
    }

    public String getFormattedHobbies() {
        return String.join(", ", hobbies);
    }

    public String getFormattedStateCity() {
        return state + " " + city;
    }
}
