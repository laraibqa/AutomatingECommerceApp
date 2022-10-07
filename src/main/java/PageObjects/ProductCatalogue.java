package PageObjects;

import ReUsableComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class ProductCatalogue extends AbstractComponents {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='card-body']/h5")
    List<WebElement> allProducts;

    @FindBy(css = ".ng-animating")
    WebElement loader;

    By addToCartBtn = By.xpath("following-sibling::button[last()]");

    By allProductsBy =  By.xpath("//div[@class='card-body']/h5");

    By toastMsg = cssSelector("#toast-container");


    public List<WebElement> getProductsList(){
        waitForElementToAppear(allProductsBy);
        return allProducts;
    }

    public WebElement getProductByName(String productName){

        WebElement productToSelect = getProductsList().stream().filter(product -> product.getText().equals(productName)).findFirst().orElse(null);
        return productToSelect;
    }

    public void addProductToCart(String productName){
        WebElement productToSelect = getProductByName(productName);
        productToSelect.findElement(addToCartBtn).click();
        waitForElementToAppear(toastMsg);
        waitForElementToDisAppear(loader);

    }

}
