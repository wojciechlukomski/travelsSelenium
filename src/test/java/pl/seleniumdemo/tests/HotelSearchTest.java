package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultPage;

import java.util.List;

public class HotelSearchTest extends BaseTest {
    
    @Test
    public void searchHotelTest() throws InterruptedException {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver, wait);
    
        List<String> hotelNames = hotelSearchPage
                .setCity("Dubai")
                .setDates("17/04/2023", "20/04/2023")
                .setTravellers(2, 2)
                .performSearch().getHotelNames();
    
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
    }
    
    @Test
    public void searchHotelWithoutNameTest() throws InterruptedException {
    
        ResultPage resultPage = new HotelSearchPage(driver, wait)
                .setDates("17/04/2023", "20/04/2023")
                .setTravellers(0, 1)
                .performSearch();
    
        Assert.assertEquals(resultPage.getHeadingText(), "No Results Found");
        Assert.assertTrue(resultPage.resultHeading.isDisplayed());
    }
}
