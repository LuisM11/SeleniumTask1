package org.marinb.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public abstract class AbstractPage {
    public static final String BASE_URL = "https://www.pastebin.com";
    protected WebDriver driver;
    protected abstract AbstractPage openPage();
    protected final int WAIT_TIMEOUT_SECONDS = 3;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    protected FluentWait<WebDriver> createWait() {
        return new FluentWait<>(driver).
                withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).
                pollingEvery(Duration.ofMillis(250)).
                ignoring(NotFoundException.class);
    }
    protected void waitElementToBeClickable(WebElement element) {
        try{
            createWait().until(ExpectedConditions.elementToBeClickable(element));
        }
        catch (TimeoutException timeoutException) {
            System.out.println("Timeout ! -> " + timeoutException.getMessage() );
        }
    }

    protected void waitUntilElementIsPresent(String id) {
        createWait().until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    private void closeProgram() {
        driver.close();
        System.exit(1);
    }



}
