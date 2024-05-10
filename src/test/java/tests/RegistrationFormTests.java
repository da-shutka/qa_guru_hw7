package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationFormTests extends RegistrationPage {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void settingsBeforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com/";
    }
    
    @Test
    void checkRegistrationFormAllFieldsTest() {
        String[] subjects = {"Accounting", "Biology"};
        String[] hobbies = {"Sports", "Music"};

        registrationPage.openPage()
                .setFirstName("Anna")
                .setLastName("Ivanova")
                .setUserEmail("anna.ivanova@mail.ru")
                .setGender("Female")
                .setUserNumber("9231552110")
                .setDateOfBirth("9", "September", "1991")
                .setSubjectsInput(subjects)
                .setHobbies(hobbies)
                .uploadPicture("src/test/resources/comma.jpg")
                .setCurrentAddress("Test address")
                .setState("Rajasthan")
                .setCity("Jaiselmer")
                .submit();

        registrationPage.checkSuccessfulSubmit()
                .checkResult("Student Name", "Anna Ivanova")
                .checkResult("Student Email", "anna.ivanova@mail.ru")
                .checkResult("Gender", "Female")
                .checkResult("Mobile", "9231552110")
                .checkResult("Date of Birth", "09 September,1991")
                .checkResult("Subjects", "Accounting, Biology")
                .checkResult("Hobbies", "Sports, Music")
                .checkResult("Picture", "comma.jpg")
                .checkResult("Address", "Test address")
                .checkResult("State and City", "Rajasthan Jaiselmer");
    }

    @Test
    void checkRegistrationFormMandatoryFieldsTest(){
        registrationPage.openPage()
                .setFirstName("Ivan")
                .setLastName("Petrov")
                .setGender("Male")
                .setUserNumber("1234567890")
                .setDateOfBirth("18", "July", "2000")
                .submit();

        registrationPage.checkSuccessfulSubmit()
                .checkResult("Student Name", "Ivan Petrov")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "1234567890")
                .checkResult("Date of Birth", "18 July,2000");
    }

    @Test
    void checkRegistrationFormNegativeTest() {
        registrationPage.openPage().submit();
        registrationPage.checkUnsuccessfulSubmit();
    }
}
