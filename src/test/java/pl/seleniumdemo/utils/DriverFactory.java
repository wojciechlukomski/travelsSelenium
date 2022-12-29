package pl.seleniumdemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    
    public static WebDriver getDriver(String name) {
        if (name.equals("safari")) {
            WebDriverManager.safaridriver().setup();
            return new SafariDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }
}
