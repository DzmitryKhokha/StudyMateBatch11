package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.CoursesPage;
import pages.LoginPage;
import pages.MainPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

public class CoursesTests extends BaseUI {

    LoginPage loginPage = new LoginPage();
    CoursesPage coursesPage =new CoursesPage();
    Faker faker = new Faker();
    MainPage mainPage = new MainPage();

    @Test
    public void createNewCourse() throws InterruptedException {
        loginPage.loginWithCorrectCredentials(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));
        waitAndClick(mainPage.courses);

        waitAndClick(coursesPage.createCourseBtn);
        coursesPage.uploadThePhoto.sendKeys("/Users/nargizasulaimankulova/Desktop/StudyMateBatch11/src/test/resources/course-learning.jpeg");
        coursesPage.courseNameInput.sendKeys(faker.funnyName().name());
        coursesPage.dateInput.sendKeys("02.02.2026");
        coursesPage.description.sendKeys(faker.lorem().sentence());
        waitAndClick(coursesPage.createBtn);

        waitUntilVisible(20,coursesPage.successAlert);
        Assertions.assertTrue(coursesPage.successAlert.isDisplayed());
    }
    @Test
    public void editCourseDetails() throws InterruptedException {
        loginPage.loginWithCorrectCredentials(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));
        waitAndClick(mainPage.courses);

        waitAndClick(coursesPage.editSign);
        coursesPage.edit.click();
        waitAndClick(coursesPage.courseNameInput);
        coursesPage.courseNameInput.sendKeys(Keys.COMMAND + "a");
        coursesPage.courseNameInput.sendKeys(Keys.DELETE);
        coursesPage.courseNameInput.sendKeys(faker.funnyName().name());

        coursesPage.createBtn.click();
        waitUntilVisible(20,coursesPage.successfullyUpdatedAlert);
        Assertions.assertTrue(coursesPage.successfullyUpdatedAlert.isDisplayed());
    }

    @Test
    public void deleteCourse() throws InterruptedException {
        loginPage.loginWithCorrectCredentials(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));
        waitAndClick(mainPage.courses);

        waitAndClick(coursesPage.editSign);
        waitAndClick(coursesPage.delete);
        coursesPage.deleteBtn.click();

        waitUntilVisible(20,coursesPage.successfullyDeletedAlert);
        Assertions.assertTrue(coursesPage.successfullyDeletedAlert.isDisplayed());

    }

}
