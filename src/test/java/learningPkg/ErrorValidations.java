package learningPkg;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest {
    @Test(groups = {"ErrorHandling"})
    public void verifyIncorrectEmail() throws InterruptedException {
        String userEmail = "laraibriaz15@gmail.com";
        String userPassword = "Lara@123";
        String expectedErrorMsg = "Incorrect email or password.";

        landingPage.loginApplication(userEmail,userPassword);
//        Thread.sleep(100000);
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

    @Test
    public void verifyIncorrectBoth() throws InterruptedException {
        String userEmail = "laraibriaz1w5@gmail.com";
        String userPassword = "Lara@123w";
        String expectedErrorMsg = "Incorrect email or password.";

        landingPage.loginApplication(userEmail,userPassword);
//        Thread.sleep(100000);
        String actualError = landingPage.getErrorMsg();

        Assert.assertEquals(actualError,expectedErrorMsg);

    }


}
