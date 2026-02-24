package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

public class LoginTests extends BaseUI {

    LoginPage loginPage = new LoginPage();

    @AfterEach
    void tearDown() {
        Driver.closeDriver();
    }

    @Test
    void happyPassLoginTest() throws InterruptedException {
        loginPage.loginWithCorrectCredentials(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        Assertions.assertTrue(Driver.getDriver().getCurrentUrl().contains("admin"));
    }

    @Test
    void sadPassLoginTest() {
        loginPage.loginWithWrongCredentials(
                ConfigurationReader.getProperty("wrongUserEmail"),
                ConfigurationReader.getProperty("wrongPassword")
        );

        String actualMessage = loginPage.errorMessage.getText();

        // Change your expected string to match the "Actual" result from your error
        String expectedMessage = "User with email admin1@codewise.com not found";

        Assertions.assertEquals(actualMessage, expectedMessage, "The error message on the UI does not match!");
    }

//    @Test
//    void failedLoginTestWithWrongCredentials() throws InterruptedException {
//        loginPage.loginWithCorrectCredentials("admin@gmail.com", "incorrectPassword");
//        waitUntilVisible(2, loginPage.errorMessage);
//        Assertions.assertTrue(loginPage.errorMessage.isDisplayed());
//    }
//
//    @Test
//    void failedLoginTestWithWrongPassword() throws InterruptedException {
//        loginPage.loginWithCorrectCredentials(ConfigurationReader.getProperty("username"), "incorrectPassword");
//        Assertions.assertTrue(loginPage.invalidCredentialsAlert.isDisplayed());
//    }
}