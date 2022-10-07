package PageObjects;

import ReUsableComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckOutPage extends AbstractComponents {
    WebDriver driver;
    public CheckOutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
//All web elements will come here

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countryTxtField;

    @FindBy(xpath = "//section[@class='ta-results list-group ng-star-inserted']")
    List<WebElement> listOffCountries;

    @FindBy(xpath = "//div[@class='actions']/a")
            WebElement checkOutBtn;

    By matchingCountries = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");

//    =========================================================
    public void enterCountry(String country){
        countryTxtField.sendKeys(country);
    }

    public void clickCountry(String country){
        enterCountry(country);
        waitForElementToAppear(matchingCountries);
        WebElement requiredCountry = listOffCountries.stream().filter(countryToSelect -> countryToSelect.getText().equals(country)).findFirst().orElse(null);
        requiredCountry.click();
    }

    public void checkOut(){
        checkOutBtn.click();
    }

}
