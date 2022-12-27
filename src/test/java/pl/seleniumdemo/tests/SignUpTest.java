package pl.seleniumdemo.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest extends BaseTest {
    
    @Test
    public void signUpTest() {
        
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver, wait);
        hotelSearchPage.openSingUpForm();
        
        SignUpPage signUpPage = new SignUpPage(driver, wait);
        signUpPage.fillUpFirstName("Wojtek");
        signUpPage.fillUpLastName("Test");
        signUpPage.fillUpPhone("111222333");
        signUpPage.fillUpEmail("@testerWojtek.pl");
        signUpPage.fillUpPassword("test123");
        signUpPage.fillUpConfirmPassword("test123");
        signUpPage.performSignUp();
        
        String lastName = "Test";
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver, wait);
        
        assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        assertEquals("Hi, Wojtek Test", loggedUserPage.getHeadingText());
    }
    
    @Test
    public void signUpTest2() {
        
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver, wait);
        hotelSearchPage.openSingUpForm();
        
        SignUpPage signUpPage = new SignUpPage(driver, wait);
        signUpPage.fillSignUpForm("", "", "", "", "");
        
        String lastName = "Test";
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver, wait);
        
        assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        assertEquals("Hi, Wojtek Test", loggedUserPage.getHeadingText());
    }
    
    @Test
    public void signUpInvalidEmailTest() {
        
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver, wait);
        hotelSearchPage.openSingUpForm();
        
        SignUpPage signUpPage = new SignUpPage(driver, wait);
        signUpPage.fillUpFirstName("Wojtek");
        signUpPage.fillUpLastName("Test");
        signUpPage.fillUpPhone("111222333");
        signUpPage.fillUpEmail(".pl");
        signUpPage.fillUpPassword("test123");
        signUpPage.fillUpConfirmPassword("test123");
        signUpPage.performSignUp();
        
        assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));
    }
    
    @Test
    public void signUpEmptyFieldsTest() {
        
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver, wait);
        hotelSearchPage.openSingUpForm();
        
        SignUpPage signUpPage = new SignUpPage(driver, wait);
        signUpPage.performSignUp();
        
        List<String> errors = signUpPage.getErrors();
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
    }
}

