package pages.homepage.productspages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.checkoutpage.CheckOutPage;
import utils.LocatorHelper;
import utils.WaitHelper;

public class SecondProductPage {
    protected final AndroidDriver driver;
    protected final WaitHelper waitHelper;
    protected final LocatorHelper locatorHelper;
    protected final CheckOutPage checkOutPage;
    private final By basketCheckValue = AppiumBy.xpath("//android.widget.TextView[@text=\"1\"]");
    private final By secondProductLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"test-Item\").instance(1)");
    private static final Logger logger = LoggerFactory.getLogger(SecondProductPage.class);
    private final By backToProductsBtn = AppiumBy.accessibilityId("test-BACK TO PRODUCTS");

    public SecondProductPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 15);
        this.locatorHelper = new LocatorHelper(driver);
        this.checkOutPage = new CheckOutPage(driver);
    }

    public void scrollToAddToCartAndClick() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().description(\"test-ADD TO CART\"))"
        ));
        locatorHelper.getAddToCartButton();
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

    public Boolean checkRemoveVisibility() {
        return locatorHelper.isRemoveButtonVisible();
    }

    public void clickRemoveBtn() {
       locatorHelper.getRemoveButton();
    }

    public void goToCheckOut(){
        locatorHelper.getCheckOutButton();
    }

    public void completeThePurchaseOfTheProduct(String name, String lastName, String postalCode) throws InterruptedException {
        checkOutPage.fillTheCheckoutFields(name,lastName,postalCode);
        if(name.equals(lastName)){
            logger.info("The user can be complete the check out with the same name and last name");
        }
        checkOutPage.clickTheContinueButton();
        Thread.sleep(1000);
        checkOutPage.clickTheFinishButton();
        waitHelper.waitForVisibilityOfElement(basketCheckValue,15);
    }

    public void clickToBackToProductsBtn() {
        waitHelper.waitForVisibilityOfElement(backToProductsBtn, 15).click();
    }

    public Boolean checkVisibilityOfTheSecProd(){
        return waitHelper.waitForVisibilityOfElement(secondProductLocator, 15).isDisplayed();
    }
}


