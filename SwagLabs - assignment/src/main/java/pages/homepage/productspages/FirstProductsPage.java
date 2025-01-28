package pages.homepage.productspages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.LocatorHelper;
import utils.WaitHelper;

public class FirstProductsPage {

    protected final AndroidDriver driver;
    protected final WaitHelper waitHelper;
    protected final LocatorHelper locatorHelper;
    private final By firstProductLocator = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-Item\"])[1]/android.view.ViewGroup/android.widget.ImageView\n");
    private final By basketCheckValue = AppiumBy.xpath("//android.widget.TextView[@text=\"1\"]");
    private final By removeBtn = AppiumBy.accessibilityId("test-REMOVE");
    private static final Logger logger = LoggerFactory.getLogger(FirstProductsPage.class);

    public FirstProductsPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 15);
        this.locatorHelper = new LocatorHelper(driver);
    }

    public void clickToTheFirstProduct(){
        waitHelper.waitForVisibilityOfElement(firstProductLocator,15).click();
    }

    public int checkTheValueOfTheBasketForTheSecondProduct(int numberOfProducts) {
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

    public void clickRemoveBtn(){
        waitHelper.waitForVisibilityOfElement(removeBtn,15).click();
        locatorHelper.checkTheValueOfTheBasket(1);
    }
}
