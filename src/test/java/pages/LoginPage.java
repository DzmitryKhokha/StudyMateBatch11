package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseUI;
import utils.Driver;

import javax.xml.xpath.XPath;
import java.time.Duration;

public class LoginPage extends BaseUI {

    WebDriver driver = Driver.getDriver();

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email")
    public WebElement email;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(xpath = "//button[text()='Log in']")
    public WebElement submitBtn;

    @FindBy(xpath = "//div[@role='alert']")
    public WebElement alert;

    @FindBy(xpath = "//p[text()='Invalid email or password']")
    public WebElement invalidCredentialsAlert;

    public void login(String userEmail, String password) throws InterruptedException {
        String loginPageURL = "https://codewise.studymate.us/login";
        driver.get(loginPageURL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(this.email));

        this.email.sendKeys(userEmail);
        this.password.sendKeys(password);
        waitAndClick(submitBtn);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://codewise.studymate.us/login")));
    }
}
