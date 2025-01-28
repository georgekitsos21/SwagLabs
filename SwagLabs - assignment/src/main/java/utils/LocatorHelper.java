package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class LocatorHelper {

    protected final AndroidDriver driver;
    protected final WaitHelper waitHelper;
    private final By addToCardBtn = AppiumBy.accessibilityId("test-ADD TO CART");
    private final By basketLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"test-Item\").instance(3)");
    private final By basketCheckValue = AppiumBy.xpath("//android.widget.TextView[@text=\"1\"]");
    private final By removeBtn = AppiumBy.accessibilityId("test-REMOVE");
    private final By checkOutBtn = AppiumBy.accessibilityId("test-CHECKOUT");
    private final By finishBtn = AppiumBy.accessibilityId("test-FINISH");
    private final By sortingIcon = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Modal Selector Button\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView");
    private final By lowToHighSorting = AppiumBy.xpath("//android.widget.TextView[@text=\"Price (low to high)\"]");
    private static final Logger logger = LoggerFactory.getLogger(LocatorHelper.class);

    public LocatorHelper(AndroidDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 10);
    }

    public void getAddToCartButton() {
        waitHelper.waitForVisibilityOfElement(addToCardBtn, 15).click();
    }

    public void getBasketLocator() {
        waitHelper.waitForVisibilityOfElement(basketLocator, 15).click();
    }

    public By getBasketCheckValue() {
        waitHelper.waitForVisibilityOfElement(basketCheckValue, 15).getText();
        return null;
    }

    public void getRemoveButton() {
        waitHelper.waitForVisibilityOfElement(removeBtn, 15).click();
    }

    public Boolean isRemoveButtonVisible() {
        try {
            return waitHelper.waitForVisibilityOfElement(removeBtn,15).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void getCheckOutButton() {
        waitHelper.waitForVisibilityOfElement(checkOutBtn, 15).click();
    }

    public void getFinishButton(){
        waitHelper.waitForVisibilityOfElement(finishBtn,15).click();
    }

    public int checkTheValueOfTheBasket(int numberOfProducts) {
        try {
            WebElement basketElement = driver.findElement(basketCheckValue);
            int basketValue = Integer.parseInt(basketElement.getText());
            logger.info("Current basket value: '{}'", basketValue);

            if (numberOfProducts == basketValue) {
                waitHelper.waitForVisibilityOfElement(basketCheckValue, 15).click();
                logger.info("Basket updated correctly (value: '{}').", basketValue);
                return basketValue;
            } else {
                logger.warn("Error: Basket value is '{}', expected '1'.", basketValue);
                return -1;
            }
        } catch (Exception e) {
            logger.error("An error occurred while checking the basket value: {}", e.getMessage());
        }
        return -1;
    }

    public void getSortingIcon(){
        waitHelper.waitForVisibilityOfElement(sortingIcon, 15).click();
    }

    public void getLowToHighOption(){
        waitHelper.waitForVisibilityOfElement(lowToHighSorting, 15).click();
    }

    public List<WebElement> waitForVisibilityOfAllElements(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}
