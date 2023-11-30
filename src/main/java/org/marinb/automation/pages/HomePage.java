package org.marinb.automation.pages;

import org.marinb.automation.config.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends AbstractPage{

    @FindBy(id = "postform-text")
    private WebElement textArea;
    @FindBy(id = "select2-postform-expiration-container")
    private WebElement expirationDropdownLabel;
    @FindBy(id = "select2-postform-expiration-results")
    private WebElement expirationDropdownList;

    @FindBy(id = "postform-name")
    private WebElement pasteTitle;

    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement createNewPasteButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    @Override
    public HomePage openPage() {
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        return this;
    }

    public HomePage writeText(String text) {
        waitElementToBeClickable(textArea);
        textArea.click();
        textArea.sendKeys(text);
        return this;
    }

    public HomePage openDropdown(){
        waitElementToBeClickable(expirationDropdownLabel);
        expirationDropdownLabel.click();
        return this;
    }

    public HomePage selectExpiration(String expiration){
        WebElement expirationOption = expirationDropdownList.findElement(By.xpath("//li[text()='" + expiration + "']"));
        waitElementToBeClickable(expirationOption);
        expirationOption.click();
        return this;
    }

    public HomePage writeTitle(String title) {
        waitElementToBeClickable(pasteTitle);
        pasteTitle.click();
        pasteTitle.sendKeys(title);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public HomePage createNewPaste()  {
        waitElementToBeClickable(createNewPasteButton);
        createNewPasteButton.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

 
}
