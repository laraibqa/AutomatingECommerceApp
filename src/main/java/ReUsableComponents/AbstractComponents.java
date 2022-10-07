package ReUsableComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement addToCart;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public void waitForElementToAppear(By elemnt){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(elemnt));
    }

    public void waitForElementToDisAppear(WebElement elemnt){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.invisibilityOf(elemnt));
    }

    public void goToCartPage(){
        addToCart.click();
    }

}
