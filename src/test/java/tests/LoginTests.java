package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
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
    void successFullLoginTest() throws InterruptedException {
        loginPage.login(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        Assertions.assertTrue(Driver.getDriver().getCurrentUrl().contains("admin"));
    }

    @Test
    void failedLoginTestWithWrongCredentials() throws InterruptedException {
        loginPage.login("admin@gmail.com", "incorrectPassword");
        waitUntilVisible(2, loginPage.alert);
        Assertions.assertTrue(loginPage.alert.isDisplayed());
    }

    @Test
    void failedLoginTestWithWrongPassword() throws InterruptedException {
        loginPage.login(ConfigurationReader.getProperty("username"), "incorrectPassword");
        Assertions.assertTrue(loginPage.invalidCredentialsAlert.isDisplayed());
    }
}