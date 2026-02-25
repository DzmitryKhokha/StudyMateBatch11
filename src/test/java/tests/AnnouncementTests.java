package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class AnnouncementTests extends BaseUI {


    static WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage();


    @Test
    public void announcementDashboardClick() throws InterruptedException {
        loginPage.loginWithCorrectCredentials(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
        explicitWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Announcements']")));
        WebElement announcementTab = driver.findElement(By.xpath("//li[normalize-space()='Announcements']"));
        waitAndClick(announcementTab);
    }

    @Test
    public void add_An_Announcement_Tab() throws InterruptedException {
        announcementDashboardClick();
        explicitWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[@class='css-1v74z38']/button")));
        WebElement addNewAnnouncementTab = driver.findElement(By.xpath("//header[@class='css-1v74z38']/button"));
        waitAndClick(addNewAnnouncementTab);

        //add text
        explicitWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='text']")));
        WebElement text = driver.findElement(By.xpath("//textarea[@name='text']"));
        sendKeys(text, "StudyMate text"); // potentially we can use Java faker

        //click on dropdown to select groups Randomly by index
        explicitWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mui-component-select-groups']")));
        WebElement dropdown = driver.findElement(By.xpath("//div[@id='mui-component-select-groups']"));
        waitAndClick(dropdown);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[@role='listbox']//li[@role='option']")));

        List<WebElement> groupLists = driver.findElements(By.xpath("//li[@role='option']"));

//        for (WebElement groupList: groupLists){
//            System.out.println(groupList.getText());
//        }
        Random random = new Random();
        int randomIndex = random.nextInt(groupLists.size());

        groupLists.get(randomIndex).click();

        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add']"));
        waitAndClick(addButton);


    }
}

