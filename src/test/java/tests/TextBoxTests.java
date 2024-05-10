package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxTests extends TextBoxPage {
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void settingsBeforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com/";
    }

    @Test
    void checkTextBoxFormAllFieldsTest() {
        textBoxPage.openPage()
                .setFullName("Petr Petrov")
                .setUserEmail("petr.petrov@mail.ru")
                .setCurrentAddress("Test current address")
                .setPermanentAddress("Test permanent address")
                .submit();

        textBoxPage.checkResult("Name", "Petr Petrov")
                .checkResult("Email", "petr.petrov@mail.ru")
                .checkResult("Current Address", "Test current address")
                .checkResult("Permananet Address", "Test permanent address");
    }
}
