package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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
    public void test2_createNewEvent() {

        schedulePages.selectCalendarDay("2026-02-06");
        schedulePages.clickCreateEvent();

        schedulePages.setDate("06.02.2026");
        schedulePages.setEventName("Coding session");
        schedulePages.setStartTime("10:00");
        schedulePages.setEndTime("11:00");

        schedulePages.selectForWhom("Maurice Minor");

        Assertions.assertTrue(schedulePages.isPublishEnabled());

        schedulePages.clickPublish();
    }

    // Test 3: Negative - Save/Publish disabled when required fields are empty
    @Test
    public void test3_negative_cannotSaveWithoutRequiredFields() {

        schedulePages.selectCalendarDay("2026-02-06");

        schedulePages.clickCreateEvent();


        Assertions.assertFalse(schedulePages.saveBtn.isEnabled());
        Assertions.assertFalse(schedulePages.publishBtn.isEnabled());

        Assertions.assertTrue(schedulePages.isTitleInvalid());
    }

}
