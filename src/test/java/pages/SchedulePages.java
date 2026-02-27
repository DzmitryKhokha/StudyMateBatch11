package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class SchedulePages {
    WebDriver driver = Driver.getDriver();

    public SchedulePages() {
        PageFactory.initElements(driver, this);
    }

    //test1-------------view existing event---------------
    @FindBy(css = "div.fc-daygrid-day-events")
    public WebElement dayEventsContainer;

    @FindBy(xpath = "//a[contains(@class,'fc-daygrid-event') and contains(.,'math')]")
    public WebElement mathEvent;

    @FindBy(xpath = "//aside")
    public WebElement eventDetailsPanel;

    @FindBy(xpath = "//aside//*[contains(text(),'4 February')]")
    public WebElement detailsTitle;

    @FindBy(xpath = "//aside//*[contains(text(),'Publish')]")
    public WebElement detailsPublishStatus;

    @FindBy(xpath = "//aside//*[contains(text(),'08:00') and contains(text(),'09:00')]")
    public WebElement detailsTime;

    //test2-------------Create event-----------------

    @FindBy(xpath = "//button[contains(.,'Create event')]")
    public WebElement createBtn;

    @FindBy(id = "startDate")
    public WebElement dateInput;

    @FindBy(id = "title")
    public WebElement nameInput;

    @FindBy(xpath = "//li[@role='option' and normalize-space()='Maurice Minor']")
    public WebElement mauriceOption;

    @FindBy(id = "startTime")
    public WebElement startTime;

    @FindBy(id = "endTime")
    public WebElement endTime;

    @FindBy(xpath = "//label[normalize-space()='For whom']/following::div[@role='button'][1]")
    public WebElement forWhomDropdown;


    @FindBy(xpath = "//button[normalize-space()='Save']/following-sibling::button[normalize-space()='Publish']")
    public WebElement publishBtn;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    public  WebElement saveBtn;
    ///



}
