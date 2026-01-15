package ui;

import utils.RandomUtils;

import java.util.List;

public class TestData {
    public final String firstName = RandomUtils.firstName();
    public final String lastName = RandomUtils.lastName();
    public final String email = RandomUtils.email();
    public final String phone = RandomUtils.phone();
    public final String address = RandomUtils.address();
    public final String gender = RandomUtils.gender();
    public final String day = RandomUtils.randomDay();
    public final String month = RandomUtils.randomMonth();
    public final String year = RandomUtils.randomYear();
    public final List<String> hobbies = RandomUtils.randomHobbies();
    public final String state = RandomUtils.randomState();
    public final String city = RandomUtils.randomCity(state);
    public final String subjectLetter = RandomUtils.randomSubjectLetter();
    public final String subject = subjectLetter;
    public final String fileType = RandomUtils.randomFileType();
    public final String classpathFile = "png".equals(fileType) ? "img/png.png" : "img/txt.txt";
    public final String expectedFileName = "png".equals(fileType) ? "png.png" : "txt.txt";

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
