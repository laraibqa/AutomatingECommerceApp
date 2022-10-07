package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    WebDriver driver;
    public  LandingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

//    WebElement userEmail =  driver.findElement(By.id("userEmail"));
//    WebElement userPassword =  driver.findElement(By.id("userEmail"));

//    PageFactory
    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement loginBtn;

    public void loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginBtn.click();
    }

    public void goTo(String url){
        driver.get(url);
    }


}
