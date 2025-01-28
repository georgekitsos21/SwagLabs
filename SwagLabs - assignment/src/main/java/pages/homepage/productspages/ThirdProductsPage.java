package pages.homepage.productspages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.WaitHelper;

public class ThirdProductsPage {

    protected final AndroidDriver driver;
    protected final WaitHelper waitHelper;
    private final By thirdProductLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"test-Item\").instance(3)");

    public ThirdProductsPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 15);
    }

    public void clickToTheThirdProduct(){
        waitHelper.waitForVisibilityOfElement(thirdProductLocator,15).click();
    }
}
