package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomData {
    private final Faker faker = new Faker(new Locale("eu"));

    public String firstName() {
        return faker.name().firstName();
    }

    public String lastName() {
        return faker.name().lastName();
    }

    public String email() {
        return faker.internet().emailAddress();
    }

    public String phoneRu() {
        return "8" + faker.phoneNumber().subscriberNumber(9);
    }

    public String address() {
        return faker.address().fullAddress();
    }
}
