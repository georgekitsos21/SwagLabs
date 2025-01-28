package pages.homepage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitHelper;
import java.util.NoSuchElementException;

public class HomePage {
    protected final AndroidDriver driver;
    private final WaitHelper waitHelper;
    private final By cardDropZone = AppiumBy.accessibilityId("test-Cart drop zone");
    private final By menuIcon = AppiumBy.accessibilityId("test-Menu");
    private final By logOutElement = AppiumBy.accessibilityId("test-LOGOUT");
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 10);
    }

    public Boolean isCardDropZoneIsDisplayed() {
        try {
            logger.info("Attempting to check visibility of the card drop zone...");

            WebElement element = waitHelper.waitForVisibilityOfElement(cardDropZone, 5);

            if (element.isDisplayed()) {
                logger.info("Card drop zone is visible.");
                return true;
            } else {
                logger.warn("Card drop zone is not visible.");
                return false;
            }
        } catch (NoSuchElementException e) {
            logger.error("Card drop zone element was not found: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            logger.error("An unexpected error occurred while checking card drop zone visibility: {}", e.getMessage(), e);
            return false;
        }
    }

    public void logOutFromTheParticularUser() {
        waitHelper.waitForVisibilityOfElement(menuIcon,1).click();
        logger.info("Clicked on user menu for logout.");

        waitHelper.waitForVisibilityOfElement(logOutElement,3).click();
        logger.info("Clicked on 'Log Out' button.");
    }
}