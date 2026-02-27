package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.TrashPages;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

import java.time.Duration;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrashTests extends BaseUI {



    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    TrashPages trashPages = new TrashPages();


    @AfterEach
    void tearDown() {
        Driver.closeDriver();
    }

@Test
    public void openTrashPage() throws InterruptedException {

    loginPage.loginWithCorrectCredentials(ConfigurationReader.getProperty("username"),
            ConfigurationReader.getProperty("password"));

    waitAndClick(mainPage.trash);

    Assertions.assertTrue(Driver.getDriver().getCurrentUrl().contains("deleted-items"));

}

@Test
public void DeleteForeverRowShouldShowDeletedConfirmation() throws InterruptedException {

    loginPage.loginWithCorrectCredentials(ConfigurationReader.getProperty("username"),
            ConfigurationReader.getProperty("password"));

    waitAndClick(mainPage.trash);
    waitUntilVisible(10, trashPages.trashHeader);
    assertTrue(trashPages.trashHeader.isDisplayed());


    new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10))
            .until(driver -> trashPages.rows.size() > 0);

    assertTrue(trashPages.rows.size() > 0, "Trash must have at least one row");

    // choose random row index
    int randomIndex = new Random().nextInt(trashPages.rows.size());

    // click delete forever
    waitAndClick(trashPages.deleteForeverButtons.get(randomIndex));

    // wait confirmation
    waitUntilVisible(10, trashPages.SuccessfullyDeletedConfirmation);

    // assert confirmation text
    assertTrue(trashPages.SuccessfullyDeletedConfirmation.getText().contains("Data deleted successfully"),
            "Deleted confirmation message should appear after delete forever");

}
    @Test
    public void recoverAnyRowShouldDisappearFromTrash() throws InterruptedException {

        loginPage.loginWithCorrectCredentials(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        waitAndClick(mainPage.trash);
        waitUntilVisible(10, trashPages.trashHeader);
        assertTrue(trashPages.trashHeader.isDisplayed());


        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10))
                .until(d -> trashPages.deleteForeverButtons.size() > 0);

        // assert at least one row
        assertTrue(trashPages.deleteForeverButtons.size() > 0, "Trash must have at least one row");

        // choose random row index
        int randomIndex = new Random().nextInt(trashPages.rows.size());

        // click recover (your existing element)
        waitAndClick(trashPages.recoverData.get(randomIndex));

        // wait confirmation
        waitUntilVisible(10, trashPages.dataSuccessfullyRecoveredConfirmation);

        // assert confirmation text
        assertTrue(trashPages.dataSuccessfullyRecoveredConfirmation.getText().contains("Data successfully recovered"),
                "Recovered confirmation message should appear after recover");
    }

    }















