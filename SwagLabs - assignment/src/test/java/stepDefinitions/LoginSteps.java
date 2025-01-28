package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class LoginSteps extends BaseTest {

    @When("I launch the app and I use the standard user")
    public void fillTheLoginFields() {
        loginPage.waitForAppToLoad();
        loginPage.fillUserName("standard_user");
        loginPage.fillPassword("secret_sauce");
    }

    @When("I click the log in button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("I should be successfully logged in and I should see the product header")
    public void testSuccessfullyLogin() {
        boolean isProductElementVisible = homePage.isCardDropZoneIsDisplayed();
        assertTrue("The Product header should be visible after successful login.", isProductElementVisible);
    }

    @Then("I should not be successfully logged in and I should not be able to see the product header")
    public void testUnsuccessfullyLogin(){
        boolean isProductElementVisible = homePage.isCardDropZoneIsDisplayed();
        assertFalse("The card should be visible after successful login.", isProductElementVisible);
    }

    @When("I launch the app and I use the locked user")
    public void fillTheLoginFieldsForLockedUser() {
        loginPage.waitForAppToLoad();
        loginPage.fillUserName("locked_out_user");
        loginPage.fillPassword("secret_sauce");
    }

    @Then("I should see a an error message about the locked account")
    public void errorMsgForLockedAcc(){
        String expectedErrorMessage = "Sorry, this user has been locked out.";
        String actualErrorMessage = loginPage.getErrorMessageForLockedAcc();
        assertEquals("The error message for the locked account is incorrect.", actualErrorMessage, expectedErrorMessage);
    }

    @When("I launch the app and I use the problematic user")
    public void fillTheLoginFieldsForProblemUser() {
        loginPage.waitForAppToLoad();
        loginPage.fillUserName("problem_user");
        loginPage.fillPassword("secret_sauce");
    }

    @Then("I should see a an error message about the problematic account")
    public void errorMsgForProblematicAcc(){
        String expectedErrorMessage = "Sorry, this user has been locked out.";
        String actualErrorMessage = loginPage.getErrorMessageForProblematicAcc();
        assertEquals("The error message for the locked account is incorrect.", actualErrorMessage, expectedErrorMessage);
    }
}




