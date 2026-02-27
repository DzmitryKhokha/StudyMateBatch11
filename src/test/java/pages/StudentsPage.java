package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseUI;
import utils.Driver;

public class StudentsPage extends BaseUI {
    WebDriver driver = Driver.getDriver();

    public StudentsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(.,'Add student') or contains(.,'Add Student') or contains(.,'Add')]")
    public WebElement addStudentBtn;

    // VERY IMPORTANT: use flexible CSS with comma (OR)
    @FindBy(css = "input[name='firstName'], input[placeholder*='First']")
    public WebElement firstNameInput;

    @FindBy(css = "input[name='lastName'], input[placeholder*='Last']")
    public WebElement lastNameInput;

    @FindBy(css = "input[name='email'], input[placeholder*='Email'], input[type='email']")
    public WebElement emailInput;

    @FindBy(css = "input[name='phoneNumber'], input[name='phone'], input[placeholder*='Phone']")
    public WebElement phoneInput;

    @FindBy(xpath = "//button[contains(.,'Submit') or contains(.,'Save') or contains(.,'Add')]")
    public WebElement submitBtn;

    @FindBy(css = "div[role='alert'], .Toastify__toast, .alert")
    public WebElement successAlert;

    public void clickAddStudent() {
        waitAndClick(addStudentBtn);
    }

    public void fillStudentForm(String first, String last, String email, String phone) {
        clearAndSendKeys(firstNameInput, first);
        clearAndSendKeys(lastNameInput, last);
        clearAndSendKeys(emailInput, email);
        clearAndSendKeys(phoneInput, phone);
    }

    public void submit() {
        waitAndClick(submitBtn);
    }

    public boolean isSuccessVisible() {
        waitUntilVisible(10,successAlert);
        return successAlert.isDisplayed();
    }
}


