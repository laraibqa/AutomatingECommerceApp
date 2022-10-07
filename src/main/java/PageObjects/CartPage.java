package PageObjects;

import ReUsableComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CartPage extends AbstractComponents {
    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
//All web elements will come here

    @FindBy(xpath = "//ul[@class='cartWrap ng-star-inserted']//li//h3")
    List<WebElement> productsInCart;

    @FindBy(xpath = "//button[@type='button' and text()='Checkout']")
    WebElement checkoutBtn;
//    =========================================================
    public Boolean productExistsInCart(String productName){

       Boolean productFound = productsInCart.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));

        return productFound;
//        driver.findElement(By.xpath("//button[@type='button' and text()='Checkout']")).click();
    }

    public void proceedToCheckout(){
        checkoutBtn.click();
    }
}
