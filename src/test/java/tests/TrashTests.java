package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void recoverAnyRowShouldDisappearFromTrash(){

    // login
    loginPage.loginWithCorrectCredentials(
            ConfigurationReader.getProperty("username"),
            ConfigurationReader.getProperty("password")
    );

    // open Trash
    Driver.getDriver().get(
            ConfigurationReader.getProperty("url") + "/admin/deleted-items"
    );

    waitUntilVisible(20, trashPages.trashHeader);

    // убедимся, что есть строки
    assertTrue(trashPages.rows.size() > 0,
            "Trash must have at least one row");

    int beforeCount = trashPages.rows.size();

    // выбираем случайную строку по индексу
    int randomIndex = new Random().nextInt(trashPages.rows.size());

    // кликаем delete forever в этой строке
    waitAndClick(trashPages.deleteForeverButtons.get(randomIndex));

    // ждём уменьшения количества строк
    new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20))
            .until(driver -> trashPages.rows.size() < beforeCount);

    // проверяем, что строк стало меньше
    assertTrue(trashPages.rows.size() < beforeCount,
            "Row count should decrease after delete forever");
}
}









    }
