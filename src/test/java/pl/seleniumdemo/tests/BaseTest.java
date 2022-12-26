package pl.seleniumdemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    @BeforeMethod
    public void setup() {
        WebDriverManager.safaridriver().setup();
        driver = new SafariDriver();
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }
    
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
