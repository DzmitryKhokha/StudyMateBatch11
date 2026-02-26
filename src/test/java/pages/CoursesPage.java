package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class CoursesPage {
    WebDriver driver = Driver.getDriver();

    public CoursesPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(tagName  = "button")
    public WebElement createCourseBtn;

    @FindBy(xpath = "//input[@type='file']")
    public WebElement uploadThePhoto;

    @FindBy(name ="courseName")
    public WebElement courseNameInput;

    @FindBy(xpath = "//input[@placeholder='dd.mm.y']")
    public WebElement dateInput;

    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement description;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement createBtn;

    @FindBy(xpath = "//p[contains(text(),'successfully')]")
    public WebElement successAlert;

    @FindBy(xpath = "(//button//*[name()='svg'])[2]")
    public WebElement editSign;

    @FindBy(xpath = "(//li[text()='Edit'])[1]")
    public WebElement edit;

    @FindBy(xpath = "//p[text()='The course successfully updated']")
    public WebElement successfullyUpdatedAlert;

    @FindBy(xpath = "(//li[text()='Delete'])[1]")
    public WebElement delete;

    @FindBy(xpath = "//button[text()='Delete']")
    public WebElement deleteBtn;

    @FindBy(xpath = "//p[text()='The course successfully deleted']")
    public WebElement successfullyDeletedAlert;


}
