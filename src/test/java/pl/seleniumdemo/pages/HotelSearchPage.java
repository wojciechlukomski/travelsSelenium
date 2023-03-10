package pl.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HotelSearchPage {
    
    private static final Logger logger = LogManager.getLogger();
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;
    
    @FindBy(css = ".select2-input.select2-focused")
    private WebElement searchHotelInput;
    
    @FindBy(name = "checkin")
    private WebElement checkinInput;
    
    @FindBy(name = "checkout")
    private WebElement checkoutInput;
    
    @FindBy(id = "travellersInput")
    private WebElement travellersInput;
    
    @FindBy(id = "adultPlusBtn")
    private WebElement aduldPlusBtn;
    
    @FindBy(id = "childPlusBtn")
    private WebElement childPlsBtn;
    
    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;
    
    //SignupPage
    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;
    
    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> signUpLink;
    
    public HotelSearchPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.wait = wait;
        this.driver = driver;
    }
    
    public void setCity(String cityName) {
        logger.info("Setting city " + cityName);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
        logger.info("Setting city DONE");
    }
    
    public void setDates(String checkin, String checkout) {
        logger.info("Setting check in and check out");
        checkinInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
        logger.info("Setting check in and check out DONE");
    }
    
    public void setTravellers(int adultsToAdd, int childToAdd) {
        logger.info("Adding travellers");
        travellersInput.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("adultPlusBtn")));
        addTravellers(aduldPlusBtn, adultsToAdd);
        addTravellers(childPlsBtn, childToAdd);
        logger.info("Adding travellers DONE");
    }
    
    public void addTravellers(WebElement element, int numberToAdd) {
        for (int i = 0; i < numberToAdd; i++) {
            element.click();
        }
    }
    
    public void performSearch() {
        logger.info("Performing search");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' Search']")));
        searchButton.click();
        logger.info("Performing search DONE");
    }
    
    public void openSingUpForm() {
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpLink.get(1).click();
    }
}
