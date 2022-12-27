package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class ResultPage {
    
    private final WebDriverWait wait;
    
    @FindBy(xpath = "//h4[contains(@class,'list_title')]//b")
    private List<WebElement> hotelList;
    
    @FindBy(xpath = "//div[@class='itemscontainer']//h2")
    public WebElement resultHeading;
    
    public ResultPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.wait = wait;
    }
    
    public String getHeadingText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='itemscontainer']//h2")));
        return resultHeading.getText();
    }
    
    public List<String> getHotelNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(@class,'list_title')]//b")));
        return hotelList.stream().map(el -> el.getAttribute("textContent")).collect(Collectors.toList());
    }
}
