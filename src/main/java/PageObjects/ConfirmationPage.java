package PageObjects;

import ReUsableComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ConfirmationPage extends AbstractComponents {
    WebDriver driver;
    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
//All web elements will come here

    @FindBy(xpath = "//h1[@class='hero-primary']")
    WebElement thankYouMsg;

    By matchingCountries = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");

//    =========================================================

    public String getThankYouMsg(){
        return thankYouMsg.getText();
    }

}
