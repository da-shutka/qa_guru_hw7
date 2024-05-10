package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.TestData;

public class RegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @BeforeEach
    void prepareTestData() {
        testData.prepareRegistrationTestData();
    }
    
    @Test
    void checkRegistrationFormAllFieldsTest() {
        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setUserEmail(testData.userEmail)
                .setGender(testData.gender)
                .setUserNumber(testData.phoneNumber)
                .setDateOfBirth(testData.birthDay)
                .setSubjectsInput(testData.subjects)
                .setHobbies(testData.hobbies)
                .uploadPicture(testData.file)
                .setCurrentAddress(testData.currentAddress)
                .setState(testData.state)
                .setCity(testData.city)
                .submit();

        registrationPage.checkSuccessfulSubmit()
                .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Student Email", testData.userEmail)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.phoneNumber)
                .checkResult("Date of Birth", String.format(
                        "%s %s,%s",
                        testData.birthDay.getFirst(),
                        testData.birthDay.get(1),
                        testData.birthDay.getLast()))
                .checkResult("Subjects", String.join(", ", testData.subjects))
                .checkResult("Hobbies", String.join(", ", testData.hobbies))
                .checkResult("Picture", testData.file)
                .checkResult("Address", testData.currentAddress)
                .checkResult("State and City", testData.state + " " + testData.city);
    }

    @Test
    void checkRegistrationFormMandatoryFieldsTest(){
        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setUserNumber(testData.phoneNumber)
                .setDateOfBirth(testData.birthDay)
                .submit();

        registrationPage.checkSuccessfulSubmit()
                .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.phoneNumber)
                .checkResult("Date of Birth", String.format(
                        "%s %s,%s",
                        testData.birthDay.getFirst(),
                        testData.birthDay.get(1),
                        testData.birthDay.getLast()))
        ;
    }

    @Test
    void checkRegistrationFormNegativeTest() {
        registrationPage.openPage().submit();
        registrationPage.checkUnsuccessfulSubmit();
    }
}
