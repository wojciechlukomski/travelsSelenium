package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoggedUserPage {
    
    protected WebDriverWait wait;
    protected WebDriver driver;
    
    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement heading;
    
    public LoggedUserPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }
    
    public String getHeadingText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//h3[@class='RTL']"))));
        return heading.getText();
    }
}
