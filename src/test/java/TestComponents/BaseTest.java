package TestComponents;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage ;
    public WebDriver initializeDriver() throws IOException {
//        properties class
        Properties globalProperties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/Resources/GlobalData.properties");

        globalProperties.load(fileInputStream);

        String browserName = globalProperties.getProperty("browser");

        if(browserName.contains("chrome")){
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
            }
        else if (browserName.contains("fireFox")){
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
            }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo("https://rahulshettyacademy.com/client");

        return landingPage;

    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
