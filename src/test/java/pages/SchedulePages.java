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

    @FindBy(xpath = "//button[normalize-space()='Save']/following-sibling::button[normalize-space()='Publish']")
    public WebElement publishBtn;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    public  WebElement saveBtn;

    //methods

    public void selectCalendarDay(String isoDate) {
        Driver.getDriver()
                .findElement(By.cssSelector("td[data-date='" + isoDate + "']"))
                .click();
    }

    public void clickCreateEvent() {
        createBtn.click();
    }

    public void setDate(String date_dd_mm_yyyy) {
        dateInput.click();
        dateInput.clear();
        dateInput.sendKeys(date_dd_mm_yyyy);
        dateInput.sendKeys(Keys.TAB);
    }

    public void setEventName(String name) {
        nameInput.click();
        nameInput.clear();
        nameInput.sendKeys(name);
        nameInput.sendKeys(Keys.TAB);
    }

    public void setStartTime(String time) {
        startTime.click();
        startTime.clear();
        startTime.sendKeys(time);
        startTime.sendKeys(Keys.ENTER);
        startTime.sendKeys(Keys.TAB);
    }

    public void setEndTime(String time) {
        endTime.click();
        endTime.clear();
        endTime.sendKeys(time);
        endTime.sendKeys(Keys.ENTER);
        endTime.sendKeys(Keys.TAB);
    }

    public void selectForWhom(String name) {
        Driver.getDriver()
                .findElement(By.xpath("//li[@role='option' and normalize-space()='" + name + "']"))
                .click();
    }

    public void clickSave() {
        saveBtn.click();
    }

    public void clickPublish() {
        publishBtn.click();
    }

    public boolean isTitleInvalid() {
        return "true".equalsIgnoreCase(nameInput.getAttribute("aria-invalid"));
    }

    public boolean isPublishEnabled() {
        return publishBtn.isEnabled();


    }
    public void selectMaurice(){
        mauriceOption.click();
    }
}
