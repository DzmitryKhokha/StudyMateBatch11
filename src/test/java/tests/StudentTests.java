package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.StudentsPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

public class StudentTests extends BaseUI {

    LoginPage loginPage;
    MainPage mainPage;
    StudentsPage studentsPage;
    Faker faker;

    @BeforeEach
    void setUp() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage = new LoginPage();
        mainPage = new MainPage();
        studentsPage = new StudentsPage();
        faker = new Faker();


    }

    @AfterEach
    void tearDown() {
        Driver.closeDriver();
    }

    @Test
    void addStudentTest() throws InterruptedException {
        loginPage.loginWithCorrectCredentials(
                ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password")
        );

        waitAndClick(mainPage.students);
        studentsPage.clickAddStudent();

        String first = faker.name().firstName();
        String last = faker.name().lastName();
        String email = "auto_" + System.currentTimeMillis() + "@gmail.com";
        String phone = "312" + faker.number().digits(7);

        studentsPage.fillStudentForm(first, last, email, phone);
        studentsPage.submit();

        Assertions.assertTrue(studentsPage.isSuccessVisible(), "Success alert was NOT displayed!");
    }
}


