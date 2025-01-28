package pages.homepage.productspages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.LocatorHelper;
import utils.WaitHelper;
import java.util.List;

public class ProductsPage {

    protected final AndroidDriver driver;
    protected final WaitHelper waitHelper;
    protected final LocatorHelper locatorHelper;
    private final By productsLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\")");
    private final By secondProductLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"test-Item\").instance(1)");
    private final By basketLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"test-Item\").instance(3)");
    private final By menuIcon = AppiumBy.accessibilityId("test-Menu");
    private final By allItemsMenuElement = AppiumBy.accessibilityId("test-ALL ITEMS");
    private final By lowestPriceLocator = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"test-Price\" and @text=\"$7.99\"]");
    private static final Logger logger = LoggerFactory.getLogger(ProductsPage.class);

    public ProductsPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 10);
        this.locatorHelper = new LocatorHelper(driver);
    }

    public void clickMenu() {
        waitHelper.waitForVisibilityOfElement(menuIcon, 15).click();
    }

    public void clickAllItemsOption() {
        waitHelper.waitForVisibilityOfElement(allItemsMenuElement, 15).click();
    }

    public void verifyAndSelectSecondProduct() {
        try {
            List<WebElement> productList = driver.findElements(productsLocator);
            if (productList.isEmpty()) {
                logger.warn("No products found on the page.");
                return;
            }
            logger.info("Total products found: {}", productList.size());
            waitHelper.waitForVisibilityOfElement(secondProductLocator, 15).click();
            logger.info("Clicked on the first product successfully.");
        } catch (Exception e) {
            logger.error("An error occurred while verifying and selecting the first product. Details: {}", e.getMessage(), e);
        }
    }

    public void clickSortingIcon() {
        try {
            logger.info("Attempting to click the sorting icon...");
            locatorHelper.getSortingIcon();
            locatorHelper.getLowToHighOption();
            logger.info("Successfully clicked the sorting icon.");
        } catch (NullPointerException e) {
            logger.error("Error: Sorting button is null. Check if the locator is correct or if the element is initialized.");
        } catch (Exception e) {
            logger.error("An unexpected error occurred while clicking the sorting icon.");
        }
    }

    public boolean isFirstProductPriceCorrect(String expectedPrice) {
        waitHelper.waitForVisibilityOfElement(lowestPriceLocator,15);
        String actualPriceText = driver.findElement(lowestPriceLocator).getText().replace("$", "").trim();
        return actualPriceText.equals(expectedPrice);
    }
}