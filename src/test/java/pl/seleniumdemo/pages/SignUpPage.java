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
    
    private final WebDriver driver;
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
        this.driver = driver;
    }
    
    public SignUpPage fillUpFirstName(String firstNameInput) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
        firstname.sendKeys(firstNameInput);
        return this;
    }
    
    public SignUpPage fillUpLastName(String lastNameInput) {
        lastname.sendKeys(lastNameInput);
        return this;
    }
    
    public SignUpPage fillUpPhone(String phoneInput) {
        phone.sendKeys(phoneInput);
        return this;
    }
    
    public SignUpPage fillUpEmail(String emailInput) {
        email.sendKeys(emailInput);
        return this;
    }
    
    public SignUpPage fillUpPassword(String passwordInput) {
        password.sendKeys(passwordInput);
        return this;
    }
    
    public SignUpPage fillUpConfirmPassword(String passwordConfirmInput) {
        confirmpassword.sendKeys(passwordConfirmInput);
        return this;
    }
    
    public LoggedUserPage performSignUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' Sign Up']")));
        signUpBtn.click();
        return new LoggedUserPage(driver, wait);
    }
    
    public List<String> getErrors() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']//p")));
        return errors.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
