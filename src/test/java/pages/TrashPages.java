package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;
public class TrashPages {

    WebDriver driver = Driver.getDriver();

    public TrashPages() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[text()='Trash']")
    public WebElement trashHeader;

    @FindBy(xpath = "//p[text()='Data successfully recovered']")
    public WebElement dataSuccessfullyRecoveredConfirmation;

    @FindBy(xpath = "//p[text()='Data deleted successfully']")
    public WebElement SuccessfullyDeletedConfirmation;

    @FindBy(xpath = "//tbody/tr/td[last()]//*[name()='svg'][1]")
    public List<WebElement> recoverData;

    @FindBy(xpath = "//tbody/tr/td[last()]//*[name()='svg'][2]")
    public List<WebElement> deleteForeverButtons;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement>rows;

    public By firstRowNameCell(){
        return By.xpath("//tbody/tr[1]/td[1]");
    }

    public WebElement getRandomRow() {

        int size = rows.size();

        int randomIndex = new java.util.Random().nextInt(size);

        return rows.get(randomIndex);
    }




}
