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

public class SignUpPage {
    
    private final WebDriverWait wait;
    
    @FindBy(name = "firstname")
    private WebElement firstname;
    
    @FindBy(name = "lastname")
    private WebElement lastname;
    
    @FindBy(name = "phone")
    private WebElement phone;
    
    @FindBy(name = "email")
    private WebElement email;
    
    @FindBy(name = "password")
    private WebElement password;
    
    @FindBy(name = "confirmpassword")
    private WebElement confirmpassword;
    
    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpBtn;
    
    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> errors;
    
    public SignUpPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.wait = wait;
    }
    
    public void fillUpFirstName(String firstNameInput) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
        firstname.sendKeys(firstNameInput);
    }
    
    public void fillUpLastName(String lastNameInput) {
        lastname.sendKeys(lastNameInput);
    }
    
    public void fillUpPhone(String phoneInput) {
        phone.sendKeys(phoneInput);
    }
    
    public void fillUpEmail(String emailInput) {
        int randomNumber = (int) (Math.random() * 1000);
        //tworzenie randomowego maila
        String emailRandom = "tester" + randomNumber + emailInput;
        email.sendKeys(emailRandom);
    }
    
    public void fillUpPassword(String passwordInput) {
        password.sendKeys(passwordInput);
    }
    
    public void fillUpConfirmPassword(String passwordConfirmInput) {
        confirmpassword.sendKeys(passwordConfirmInput);
    }
    
    public void performSignUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' Sign Up']")));
        signUpBtn.click();
    }
    
    public void fillSignUpForm(String firstName, String lastName, String phone, String email, String password) {
        fillUpFirstName(firstName);
        fillUpLastName(lastName);
        fillUpPhone(phone);
        fillUpEmail(email);
        fillUpPassword(password);
        fillUpConfirmPassword(password);
        performSignUp();
    }
    
    public List<String> getErrors() {
        return errors.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
