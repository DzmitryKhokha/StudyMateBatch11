package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pages.GroupsPage;
import pages.LoginPage;
import pages.MainPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

public class GroupTests extends BaseUI {

    MainPage mainPage = new MainPage();
    GroupsPage groupsPage = new GroupsPage();
    LoginPage loginPage = new LoginPage();
    Faker faker = new Faker();

    @AfterEach
    void tearDown() {
        Driver.closeDriver();
    }

    @Test
    void deleteRandomGroupTest() throws InterruptedException {
        loginPage.login(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        waitAndClick(mainPage.groupPage);
        groupsPage.deleteRandomGroup();

        Assertions.assertTrue(Driver.getDriver().getCurrentUrl().contains("groups"));
    }

    @Test
    void openGroupPage() throws InterruptedException {
        loginPage.login(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        waitAndClick(mainPage.groupPage);

        Assertions.assertTrue(Driver.getDriver().getCurrentUrl().contains("groups"));
    }

    @Test
    void createGroupWithValidData() throws InterruptedException {
        loginPage.login(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        groupsPage.createNewGroup();
        groupsPage.groupImage.sendKeys("/Users/elenakhokha/Desktop/Studymate/src/test/resources/build.jpg");
        groupsPage.groupName.sendKeys(faker.funnyName().name());
        groupsPage.groupDate.sendKeys(groupsPage.todayDate);
        groupsPage.groupDescription.sendKeys(faker.name().lastName());

        waitAndClick(groupsPage.createBtn);
        waitUntilVisible(2, groupsPage.successAlert);

        Assertions.assertTrue(groupsPage.successAlert.isDisplayed());
    }

    @Test
    void editGroupDetails() throws InterruptedException {
        loginPage.login(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        jsClick(groupsPage.groupSubMenu);
        waitAndClick(groupsPage.editBtn);
        waitUntilVisible(1, groupsPage.groupName);

        String groupNameBefore = groupsPage.groupName.getAttribute("value");
        clearInputField(groupsPage.groupName);
        groupsPage.groupName.sendKeys(faker.funnyName().name());
        String groupNameAfter = groupsPage.groupName.getAttribute("value");

        String groupDescriptionBefore = groupsPage.groupDescription.getAttribute("value");
        clearInputField(groupsPage.groupDescription);
        groupsPage.groupDescription.sendKeys(faker.funnyName().name());
        String groupDescriptionAfter = groupsPage.groupDescription.getAttribute("value");

        Assertions.assertNotEquals(groupNameBefore, groupNameAfter);
        Assertions.assertNotEquals(groupDescriptionBefore, groupDescriptionAfter);
    }

    @Test
    void editGroupDetailsWithoutSaving() throws InterruptedException {
        loginPage.login(ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));

        waitUntilVisible(2, groupsPage.descr);
        String groupNameBefore = groupsPage.createdGroupName.getText();

        jsClick(groupsPage.groupSubMenu);
        waitAndClick(groupsPage.editBtn);
        waitUntilVisible(1, groupsPage.groupName);

        clearInputField(groupsPage.groupName);
        groupsPage.groupName.sendKeys(faker.funnyName().name());

        waitAndClick(groupsPage.cancelBtn);
        explicitWait(1);

        String groupNameAfter = groupsPage.createdGroupName.getText();
        Assertions.assertEquals(groupNameBefore, groupNameAfter);
    }
}