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
    
        int randomNumber = (int) (Math.random() * 1000);
        //tworzenie randomowego maila
        String emailRandom = "tester" + randomNumber + "@testerWojtek.pl";
        String lastName = "Test";
    
        LoggedUserPage loggedUserPage = new HotelSearchPage(driver, wait)
                .openSingUpForm()
                .fillUpFirstName("Wojtek")
                .fillUpLastName("Test")
                .fillUpPhone("111222333")
                .fillUpEmail(emailRandom)
                .fillUpPassword("test123")
                .fillUpConfirmPassword("test123")
                .performSignUp();
    
        assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        assertEquals("Hi, Wojtek Test", loggedUserPage.getHeadingText());
    }
    
    @Test
    public void signUpInvalidEmailTest() {
    
        SignUpPage signUpPage = new HotelSearchPage(driver, wait)
                .openSingUpForm()
                .fillUpFirstName("Wojtek")
                .fillUpLastName("Test")
                .fillUpPhone("111222333")
                .fillUpEmail("email.pl")
                .fillUpPassword("test123")
                .fillUpConfirmPassword("test123");
        signUpPage.performSignUp();
    
        assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));
    }
    
    @Test
    public void signUpEmptyFieldsTest() throws InterruptedException {
        
        SignUpPage signUpPage = new HotelSearchPage(driver, wait).openSingUpForm();
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

