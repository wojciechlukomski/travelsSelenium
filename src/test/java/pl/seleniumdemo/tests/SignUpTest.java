package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest extends BaseTest {
    
    @Test
    public void signUpTest() throws InterruptedException {
        
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        
        String lastName = "Test";
        int randomNumber = (int) (Math.random() * 1000);
        //tworzenie randomowego maila
        String email = "tester" + randomNumber + "@testerWojtek.pl";

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
    
    @Test(priority = 0)
    public void signUpInvalidEmailTest() throws InterruptedException {

//        Thread.sleep(1000);
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        
        String lastName = "Test";
        int randomNumber = (int) (Math.random() * 1000);
        //tworzenie randomowego maila
        String email = "tester" + randomNumber + ".pl";

//       uzycie wait explicity (w konkretnym miejscu) expected condition
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
        driver.findElement(By.name("firstname")).sendKeys("Wojtek");
        driver.findElement(By.name("lastname")).sendKeys("Test");
        driver.findElement(By.name("phone")).sendKeys("111222333");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("test123");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='RTL']")));
        
        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream().map(WebElement::getText).collect(Collectors.toList());
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']//p")));
        
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id='headersignupform']/div[2]/div/p"));
        assertTrue(errorMessage.getText().contains("The Email field must contain a valid email address."));
    }

//    @Test
//    public void signUpEmptyFields() throws InterruptedException {
//        WebDriverManager.safaridriver().setup();
//        WebDriver driver = new SafariDriver();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        driver.manage().window().maximize();
//        driver.get("http://www.kurs-selenium.pl/demo/");
//
////        Thread.sleep(1000);
//        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
//        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
//        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
//
////      List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream().map(WebElement::getTagName).collect(Collectors.toList());
//        List<String> errors = new ArrayList<>();
//        driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).size();
//
//
//        System.out.println(errors);
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertTrue(errors.contains("The Email field is required."));
//        softAssert.assertTrue(errors.contains("The Password field is required."));
//        softAssert.assertTrue(errors.contains("The Password field is required."));
//        softAssert.assertTrue(errors.contains("The First name field is required."));
//        softAssert.assertTrue(errors.contains("The Last Name field is required."));
//        softAssert.assertAll();
//    }
}

