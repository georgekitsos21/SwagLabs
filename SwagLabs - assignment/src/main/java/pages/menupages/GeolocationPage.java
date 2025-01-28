package pages.menupages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ElementHelper;
import utils.WaitHelper;

public class GeolocationPage {
    protected final AndroidDriver driver;
    private final ElementHelper elementHelper;
    private final WaitHelper waitHelper;
    private final By latitudeElement = AppiumBy.accessibilityId("test-latitude");
    private final By longitudeElement = AppiumBy.accessibilityId("test-longitude");
    private final By menuIcon = AppiumBy.accessibilityId("test-Menu");
    private final By geolocationElement = AppiumBy.accessibilityId("test-GEO LOCATION");
    private static final Logger logger = LoggerFactory.getLogger(GeolocationPage.class);

    public GeolocationPage(AndroidDriver driver) {
        this.driver = driver;
        this.elementHelper = new ElementHelper(driver);
        this.waitHelper = new WaitHelper(driver,5);
    }

    public void clickGeolocationOption() {
        waitHelper.waitForVisibilityOfElement(menuIcon,15).click();
        waitHelper.waitForVisibilityOfElement(geolocationElement,15).click();
        waitHelper.waitForAlert(10).accept();
    }

    public String getLatitudeValue() {
        try {
            logger.info("Attempting to fetch the latitude value.");
            waitHelper.waitForVisibilityOfElement(latitudeElement,15).isDisplayed();
            String latitudeValue = elementHelper.getElementText(latitudeElement);
            logger.info("Latitude value fetched successfully: {}", latitudeValue);
            return latitudeValue;
        } catch (Exception e) {
            logger.error("Error while fetching the latitude value. Details: {}", e.getMessage(), e);
        }
        return "";
    }

    public String getLongitudeValue() {
        try {
            logger.info("Attempting to fetch the longitude value.");
            waitHelper.waitForVisibilityOfElement(longitudeElement,15).isDisplayed();
            String longitudeValue = elementHelper.getElementText(longitudeElement);
            logger.info("Longitude value fetched successfully: {}", longitudeValue);
            return longitudeValue;
        } catch (Exception e) {
            logger.error("Error while fetching the longitude value. Details: {}", e.getMessage(), e);
        }
        return "";
    }
}