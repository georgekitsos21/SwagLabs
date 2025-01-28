package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;

public class GeolocationSteps extends BaseTest {

    @When("I click the Geolocation menu option")
    public void clickGeolocationFromMenu(){
        geolocationPage.clickGeolocationOption();
    }

    @Then("I should see Longitude value equals to {string}")
    public void checkLongitudeValue(String value){
        String actualLatitudeValue =  geolocationPage.getLatitudeValue();
        assertEquals("The Latitude value is not correct for the particular user.", actualLatitudeValue, value);
    }

    @Then("I should see Latitude value equals to {string}")
    public void checkLatitudeValue(String value){
        String actualLatitudeValue =  geolocationPage.getLongitudeValue();
        assertEquals("The Latitude value is not correct for the particular user.", actualLatitudeValue, value);
    }
}
