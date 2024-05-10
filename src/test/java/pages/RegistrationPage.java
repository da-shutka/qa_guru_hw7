package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.TableResultComponent;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage extends TestBasePage {
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userGender = $("#genterWrapper"),
            userNumber = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state input"),
            cityInput = $("#city input"),
            submittedForm = $("#example-modal-sizes-title-lg")
                    ;

    CalendarComponent calendarComponent = new CalendarComponent();
    TableResultComponent modal = new TableResultComponent();

    public RegistrationPage openPage() {
        open("automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        this.removeBanners();
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        userGender.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String number) {
        userNumber.setValue(number);
        return this;
    }

    public RegistrationPage setDateOfBirth(List<String> birthDay){
        calendarInput.click();
        calendarComponent.setDate(birthDay.getFirst(), birthDay.get(1), birthDay.getLast());
        return this;
    }

    public RegistrationPage setSubjectsInput(List<String> subjects) {
        for (String subject : subjects) {
            subjectsInput.setValue(subject).pressEnter();
        }
        return this;
    }

    public RegistrationPage setHobbies(List<String>  hobbies){
        for (String hobby: hobbies) {
            hobbiesInput.$$("label").filterBy(text(hobby)).first().click();
        }
        return this;
    }

    public RegistrationPage uploadPicture(String path){
        uploadPictureInput.uploadFile(new File("src/test/resources/" + path));
        return this;
    }

    public RegistrationPage setCurrentAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public RegistrationPage setState(String state) {
        stateInput.setValue(state).pressEnter();
        return this;
    }

    public RegistrationPage setCity(String city) {
        cityInput.setValue(city).pressEnter();
        return this;
    }

    public void submit(){
        $("#submit").click();
    }

    public RegistrationPage checkSuccessfulSubmit() {
        submittedForm.shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        modal.checkTableRow(key, value);
        return this;
    }

    public void checkUnsuccessfulSubmit() {
        submittedForm.shouldBe(hidden);
    }
}
