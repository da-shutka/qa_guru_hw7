package utils;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Locale;

import static utils.RandomUtils.*;
import static utils.RandomUtils.getRandomCityBasedOnState;

public class TestData{
    Faker faker = new Faker(new Locale("en"));

    public String firstName,
            lastName,
            userEmail,
            gender,
            phoneNumber,
            file,
            currentAddress,
            state,
            city,
            fullName,
            permanentAddress;

    public List<String> birthDay,
            subjects,
            hobbies;

    public void prepareRegistrationTestData() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        userEmail = faker.internet().emailAddress();
        gender = faker.demographic().sex();
        phoneNumber = faker.number().digits(10);
        file = faker.options().option("comma.jpg", "flower.png");
        currentAddress = faker.address().fullAddress();
        state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
        city = getRandomCityBasedOnState(state);
        birthDay = getRandomDate(faker.date().birthday());
        subjects = getRandomSubjects();
        hobbies = getRandomHobbies();
    }

    public void prepareTextBoxTestData() {
        fullName = faker.name().fullName();
        userEmail = faker.internet().emailAddress();
        currentAddress = faker.address().fullAddress();
        permanentAddress = faker.address().fullAddress();
    }
}
