package utils;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class RandomUtils {
    private final Faker faker = new Faker(new Locale("en"));

    public String gender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public String randomDay() {
        return String.valueOf(faker.number().numberBetween(1, 28));
    }

    public String randomMonth() {
        return faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
    }

    public String randomYear() {
        return String.valueOf(faker.number().numberBetween(1900, 2100));
    }

    /**
     * Возвращает список хобби случайной длины.
     * Метод выбирает случайное количество элементов (от 1 до общего количества
     * доступных хобби) и возвращает подсписок, начиная с первого элемента.
     */
    public List<String> randomHobbies() {
        List<String> allHobbies = new ArrayList<>(List.of("Sports", "Reading", "Music"));
        Collections.shuffle(allHobbies);
        int count = faker.number().numberBetween(1, allHobbies.size() + 1);
        return allHobbies.subList(0, count);
    }

    /**
     * Возвращает случайную букву для выбора предмета.
     * Используется для поля Subjects.
     */
    public String randomSubjectLetter() {
        String[] letters = {"e", "m", "c", "h", "p"};
        return faker.options().option(letters);
    }

    public String randomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public String randomCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }

    public String randomFileType() {
        return faker.options().option("png", "txt");
    }
}

