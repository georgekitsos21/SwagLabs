package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebViewSteps extends BaseTest {

    @When("I click the Web view menu option")
    public void clickWebViewOption() {
        webViewPage.clickWebViewOption();
    }

    @When("I add a URL to the field")
    public void addUrl() {
        webViewPage.addUrlToImportField("www.gmail.com");
    }

    @When("I click the go to site button")
    public void clickGoToSiteBtn() {
        webViewPage.clickGoToSiteBtn();
    }

    @Then("I should be able to redirect to the particular URL")
    public void checkSuccessfulImportFromUrl() {
        boolean actualWebViewUrl = webViewPage.isUrlTheExpected();
        assertTrue("The URL is not the expected", actualWebViewUrl);
    }

    @When("I add an invalid URL to the field")
    public void addInvalidUrl() {
        webViewPage.addUrlToImportField("testInvalidURL");
    }

    @Then("I should see an error message about the invalid URL")
    public void errorMsgForInvalidUrl() {
        String expectedErrorMessage = "Please provide a correct https url";
        String actualErrorMessage = webViewPage.getErrorMessageForInvalidUrl();
        assertEquals("The URL is not the expected", actualErrorMessage, expectedErrorMessage);
    }
}