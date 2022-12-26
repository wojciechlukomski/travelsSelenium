package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {
    
    @Test
    public void searchHotelTest() throws InterruptedException {
        
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("17/04/2023", "20/04/2023");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();
        
        Thread.sleep(1000);
        
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b")).stream().map(el -> el.getAttribute("textContent")).collect(Collectors.toList());
        
        hotelNames.forEach(System.out::println);
        
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
    }
    
    @Test
    public void searchHotelWithoutNameTest() throws InterruptedException {
        
        driver.findElement(By.name("checkin")).sendKeys("17/04/2023");
        driver.findElement(By.name("checkout")).sendKeys("20/04/2023");
        
        driver.findElement(By.id("travellersInput")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("adultPlusBtn"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("adultPlusBtn"))).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        
        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        Thread.sleep(1000);
        
        WebElement noResultHeading = driver.findElement(By.xpath("//div[@class='itemscontainer']//h2"));
        
        Assert.assertTrue(noResultHeading.isDisplayed());
        Assert.assertEquals(noResultHeading.getText(), "No Result Find");
    }
}
