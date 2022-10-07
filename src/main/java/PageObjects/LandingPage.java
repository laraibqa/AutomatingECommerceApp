package PageObjects;

import ReUsableComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {
    WebDriver driver;
    public  LandingPage(WebDriver driver){
        super(driver);
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

    @FindBy(css = "#toast-container")
    WebElement errorMsg;

    public void loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginBtn.click();
    }

    public void goTo(String url){
        driver.get(url);
    }

    public String getErrorMsg(){
        waitForWebElementToAppear(errorMsg);
        return errorMsg.getText();
    }

}
