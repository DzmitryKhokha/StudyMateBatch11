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

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement createBtn;

}
