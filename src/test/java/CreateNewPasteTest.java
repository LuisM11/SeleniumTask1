import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.marinb.automation.config.WebDriverFactory;
import org.marinb.automation.pages.HomePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CreateNewPasteTest {
    WebDriver driver;
    Properties properties = new Properties();

    @Before
    public void setUp() {
        try{
            properties.load(new FileReader("src/test/resources/context.properties"));
        } catch (IOException exception) {
            System.out.println("Properties weren't set");
        }
        driver = WebDriverFactory.createDriver(properties.getProperty("browser"));
    }

    @After
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    public void createNewPaste() {
        HomePage homePage = new HomePage(driver);
        try{
            homePage.openPage()
                    .writeText("Hello from WebDrive")
                    .openDropdown().selectExpiration("10 Minutes")
                    .writeTitle("helloweb")
                    .createNewPaste();
        }catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            driver.quit();
        }
    }



}
