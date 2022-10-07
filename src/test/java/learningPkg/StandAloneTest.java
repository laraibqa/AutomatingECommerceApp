package learningPkg;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class StandAloneTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String productName = "ZARA COAT 3";

        driver.get("https://rahulshettyacademy.com/client");

        driver.findElement(By.id("userEmail")).sendKeys("laraibriaz14@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Lara@123");
        driver.findElement(By.id("login")).click();

        List<WebElement> allProducts = driver.findElements(By.xpath("//section[@id='products']//div[@class='row']//div[@class='card-body']/h5"));

//ADDING AN ITEM TO CART
        WebElement productToSelect = allProducts.stream().filter(product -> product.getText().equals(productName)).findFirst().orElse(null);
        productToSelect.findElement(By.xpath("following-sibling::button[last()]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

//        for(WebElement product : allProducts){
//            String productName = product.getText();
//            System.out.println(productName);
//            if (productName.equals("ZARA COAT 3")){
//                System.out.println(product.findElement(By.xpath("following-sibling::button[2]")).getText());
//                product.findElement(By.xpath("following-sibling::button[2]")).click();
//            }
//        }

//        VERIFYING THE ELEMENT IN CART
        List<WebElement> productsInCart = driver.findElements(By.xpath("//ul[@class='cartWrap ng-star-inserted']//li//h3"));

        Boolean productFound = productsInCart.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));

        Assert.assertTrue(productFound);
        driver.findElement(By.xpath("//button[@type='button' and text()='Checkout']")).click();

//Selecting country
        Actions mouseActions = new Actions(driver);
        mouseActions.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"Pa").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
        List<WebElement> allCountries = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
        WebElement country = allCountries.stream().filter(countryToSelect -> countryToSelect.getText().equals("Pakistan")).findFirst().orElse(null);
        country.click();
//        PLACE ORDER
        driver.findElement(By.xpath("//div[@class='actions']/a")).click();

        String msgToBeDisplayed = "THANKYOU FOR THE ORDER.";
        String msgFromPage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();

        Assert.assertEquals(msgFromPage,msgToBeDisplayed);

//        for(WebElement country : allCountries){
//            System.out.println(country.getText());
//        }



    }
}
