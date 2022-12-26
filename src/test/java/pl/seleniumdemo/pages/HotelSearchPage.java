package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelSearchPage {
    
    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;
    
    @FindBy(css = ".select2-input.select2-focused")
    private WebElement searchHotelInput;
    
    @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
    private WebElement hotelMatch;
    
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
    
    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
    public void setCity(String cityName) {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        hotelMatch.click();
    }
    
    public void setDates(String checkin, String checkout) {
        checkinInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
    }
    
    public void setTravellers() {
        travellersInput.click();
        aduldPlusBtn.click();
        childPlsBtn.click();
    }
    
    public void performSearch() {
        searchButton.click();
    }
}
