package pl.seleniumdemo.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.seleniumdemo.utils.DriverFactory;

import java.time.Duration;

public class BaseTest {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    @BeforeMethod
    public void setup() {
    
        driver = DriverFactory.getDriver("safari");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }
    
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
