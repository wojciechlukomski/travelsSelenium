import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HotelSearch {
    
    @Test
    public void searchHotel(){
        WebDriverManager.safaridriver().setup();
        WebDriver driver = new SafariDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.cssSelector(".select2-input.select2-focused")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
    }
}
