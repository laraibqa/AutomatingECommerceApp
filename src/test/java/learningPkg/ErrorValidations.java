package learningPkg;

import DataPackage.JsonDataReader;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ErrorValidations extends BaseTest {
    @Test(groups = {"ErrorHandling"})
    public void verifyIncorrectEmail() throws InterruptedException {
        String userEmail = "laraibriaz15@gmail.com";
        String userPassword = "Lara@123";
        String expectedErrorMsg = "Incorrect email or password.";

        landingPage.loginApplication(userEmail,userPassword);
        Thread.sleep(1000);
        String actualError = landingPage.getErrorMsg();

        Assert.assertEquals(actualError,expectedErrorMsg);

    }

    @Test (dependsOnMethods = {"verifyIncorrectEmail"})
    public void verifyIncorrectPassword() throws InterruptedException {
        String userEmail = "laraibriaz14@gmail.com";
        String userPassword = "Lara@123sd";
        String expectedErrorMsg = "Incorrect email or password.";

        landingPage.loginApplication(userEmail,userPassword);
//        Thread.sleep(100000);
        String actualError = landingPage.getErrorMsg();

        Assert.assertEquals(actualError,expectedErrorMsg);

    }

    @Test(dataProvider = "getData")
    public void verifyIncorrectBoth(String email, String password) throws InterruptedException {
//        String userEmail = "laraibriaz1w5@gmail.com";
//        String userPassword = "Lara@123w";
        String expectedErrorMsg = "Incorrect email or password.";


        landingPage.loginApplication(email,password);
        Thread.sleep(1000);
        String actualError = landingPage.getErrorMsg();

        Assert.assertEquals(actualError,expectedErrorMsg);

    }

    @Test(dataProvider = "hashMapDataProvider")
    public void verifyHashMapWorking(HashMap<String,String> hashMapData) throws InterruptedException {
//        String userEmail = "laraibriaz1w5@gmail.com";
//        String userPassword = "Lara@123w";
        String expectedErrorMsg = "Incorrect email or password.";

        landingPage.loginApplication(hashMapData.get("email"),hashMapData.get("password"));
        Thread.sleep(1000);
        String actualError = landingPage.getErrorMsg();

        Assert.assertEquals(actualError,expectedErrorMsg);

    }

    @Test(dataProvider = "gettingDataFromJson")
    public void readingDataFromJson(HashMap<String,String> hashMapData) throws InterruptedException, IOException {
//        String userEmail = "laraibriaz1w5@gmail.com";
//        String userPassword = "Lara@123w";
        String expectedErrorMsg = "Incorrect email or password.";

        landingPage.loginApplication(hashMapData.get("email"),hashMapData.get("password"));
        Thread.sleep(1000);
        String actualError = landingPage.getErrorMsg();

        Assert.assertEquals(actualError,expectedErrorMsg);

    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][] {{"laraibriaz15@gmail.com","Lara@123w"}, {"laraibriaz16@gmail.com","Lara@123w"}};
    }

    @DataProvider
    public Object[][] hashMapDataProvider(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("email","laraibriaz17@gmail.com");
        map.put("password","laraibriaz");

        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("email","laraibriaz18@gmail.com");
        map1.put("password","laraibriaz");


        return new Object[][] {{map},{map1}};
    }

    @DataProvider
    public Object[][] gettingDataFromJson() throws IOException {
        JsonDataReader jsonDataReader = new JsonDataReader();
        List<HashMap<String, String>> jsonData = jsonDataReader.getJsonDataToMap("C:\\Users\\Atif\\IdeaProjects\\TestNGEcommerceApp\\src\\test\\java\\DataPackage\\credentials.json");

        return new Object[][] {{jsonData.get(0)}, {jsonData.get(1)}};

    }

}
