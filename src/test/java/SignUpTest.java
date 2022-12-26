import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest {
    
    @Test
    public void signUp() throws InterruptedException {
        WebDriverManager.safaridriver().setup();
        WebDriver driver = new SafariDriver();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

//        Thread.sleep(1000);
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        
        String lastName = "Test";
        int randomNumber = (int) (Math.random() * 1000);
        //tworzenie randomowego maila
        String email = "tester" + randomNumber + "@tester.pl";

//       uzycie wait explicity (w konkretnym miejscu) expected condition
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
        driver.findElement(By.name("firstname")).sendKeys("Wojtek");
        driver.findElement(By.name("lastname")).sendKeys("Test");
        driver.findElement(By.name("phone")).sendKeys("111222333");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("test123");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='RTL']")));
        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        assertTrue(heading.getText().contains(lastName));
        assertEquals("Hi, Wojtek Test", heading.getText());
    }
}
