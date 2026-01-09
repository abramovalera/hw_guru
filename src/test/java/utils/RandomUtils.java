package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomUtils {
    private static Faker faker = new Faker(new Locale("eu"));

    public String gender() {
        return faker.options().option("Male", "Female", "Other");
    }


    public static DateData randomDateCalendar() {
        String day = String.valueOf(faker.number().numberBetween(1, 28));
        String month = faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
        String year = String.valueOf(faker.number().numberBetween(1900, 2100));

        return new DateData(day, month, year);
    }

    public record DateData(String day, String month, String year) {
    }

}
