package learningPkg;

import PageObjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
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

public class OrderTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String productName = "ZARA COAT 3";
        String userEmail = "laraibriaz14@gmail.com";
        String userPassword = "Lara@123";
        String url = "https://rahulshettyacademy.com/client";
        String country = "Pakistan";
        String expectedMsg = "THANKYOU FOR THE ORDER.";

        driver.get("https://rahulshettyacademy.com/client");

//        CREATING OBJECTS OF ALL PAGES
        LandingPage landingPage = new LandingPage(driver);
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        CartPage cartPage = new CartPage(driver);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);

//        NAVIGATING TO LANDING PAGE AND LOGGING IN
        landingPage.goTo(url);
        landingPage.loginApplication(userEmail,userPassword);

//        ADDING PRODUCT TO CART
        productCatalogue.addProductToCart(productName);
        productCatalogue.goToCartPage();

//        VERIFYING CORRECT PRODUCT IS ADDED IN CART
        Boolean productMatched = cartPage.productExistsInCart(productName);
        Assert.assertTrue(productMatched);
//        PROCEEDING TO CHECKOUT
        cartPage.proceedToCheckout();

//        SELECTING COUNTRY AT CHECKOUT & PROCEEDING TO CHECKOUT
        checkOutPage.clickCountry(country);
        checkOutPage.checkOut();

//        VERIFYING CONFIRMATION MSG AT CHECKOUT
        String actualMsg = confirmationPage.getThankYouMsg();
        Assert.assertEquals(actualMsg, expectedMsg);

    }
}
