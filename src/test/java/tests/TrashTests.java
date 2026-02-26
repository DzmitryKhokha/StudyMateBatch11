package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.TrashPages;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

public class TrashTests extends BaseUI {



    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    TrashPages trashPage = new TrashPages();

    @BeforeEach
    void loginTrashPage() throws InterruptedException {
        loginPage.loginWithCorrectCredentials(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        waitAndClick(mainPage.trash);
    }

    @AfterEach
    void tearDown() {
        Driver.closeDriver();
    }

    @Test
    void openTrashTab_shouldOpenTrashUrl() {
        Assertions.assertTrue(
                Driver.getDriver().getCurrentUrl().contains("deleted-items"),
                "URL should contain deleted-items"
        );


    }}
