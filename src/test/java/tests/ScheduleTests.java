package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.LoginPage;
import pages.MainPage;
import pages.SchedulePages;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

public class ScheduleTests extends BaseUI {
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    SchedulePages schedulePages = new SchedulePages();

    @BeforeEach
    void setUp() throws InterruptedException {
        loginPage.loginWithCorrectCredentials(
                ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password")
        );

        waitAndClick(mainPage.schedule);
    }

    @AfterEach
    void tearDown() {
        Driver.closeDriver();
    }


    @Test
    void viewExistingEventsForSelectedDay() {
        waitUntilVisible(5, schedulePages.dayEventsContainer);
        Assertions.assertTrue(schedulePages.dayEventsContainer.isDisplayed());

        waitAndClick(schedulePages.mathEvent);

        waitUntilVisible(5, schedulePages.eventDetailsPanel);
        Assertions.assertTrue(schedulePages.eventDetailsPanel.isDisplayed());

        Assertions.assertTrue(schedulePages.detailsTitle.isDisplayed());
        Assertions.assertTrue(schedulePages.detailsTime.isDisplayed());
        Assertions.assertTrue(schedulePages.detailsPublishStatus.isDisplayed());
    }



    // Test 2: Create new event
    @Test
    public void test2CreateNewEvent() {
        waitUntilClickable(10, schedulePages.createBtn);
        schedulePages.createBtn.click();

        waitUntilVisible(10, schedulePages.nameInput);
        schedulePages.nameInput.click();
        schedulePages.nameInput.clear();
        schedulePages.nameInput.sendKeys("Coding Session");

        waitUntilClickable(10, schedulePages.dateInput);
        schedulePages.dateInput.click();
        schedulePages.dateInput.clear();
        schedulePages.dateInput.sendKeys("06.02.2026");
        schedulePages.dateInput.sendKeys(Keys.TAB);

        waitUntilClickable(10, schedulePages.startTime);
        schedulePages.startTime.click();
        schedulePages.startTime.clear();
        schedulePages.startTime.sendKeys("10:00");
        schedulePages.startTime.sendKeys(Keys.ENTER);

        waitUntilClickable(10, schedulePages.endTime);
        schedulePages.endTime.click();
        schedulePages.endTime.clear();
        schedulePages.endTime.sendKeys("11:00");
        schedulePages.endTime.sendKeys(Keys.ENTER);


        waitUntilClickable(10, schedulePages.forWhomDropdown);
        schedulePages.forWhomDropdown.click();

        waitUntilClickable(10, schedulePages.mauriceOption);
        schedulePages.mauriceOption.click();


        Assertions.assertTrue(schedulePages.publishBtn.isEnabled());

        waitUntilClickable(10, schedulePages.publishBtn);
        schedulePages.publishBtn.click();
    }




    // Test 3: Negative - Save/Publish disabled when required fields are empty
    @Test
    public void test3NegativeCannotSaveWithoutRequiredFields() {

        waitUntilClickable(10, schedulePages.createBtn);
        waitAndClick(schedulePages.createBtn);
        waitUntilVisible(10,schedulePages.nameInput);

        waitUntilClickable(10, schedulePages.saveBtn);
        waitAndClick(schedulePages.saveBtn);

        Assertions.assertFalse(schedulePages.publishBtn.isEnabled());

        Assertions.assertEquals("true", schedulePages.nameInput.getAttribute("aria-invalid"));
    }

}
