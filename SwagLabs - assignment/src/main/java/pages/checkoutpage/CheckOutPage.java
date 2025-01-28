package pages.checkoutpage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.LocatorHelper;
import utils.WaitHelper;

public class CheckOutPage {

    protected final AndroidDriver driver;
    protected final WaitHelper waitHelper;
    protected final LocatorHelper locatorHelper;
    private final By firstNameInput = AppiumBy.accessibilityId("test-First Name");
    private final By lastNameInput = AppiumBy.accessibilityId("test-Last Name");
    private final By postalCodeInput = AppiumBy.accessibilityId("test-Zip/Postal Code");
    private final By cancelBtn = AppiumBy.accessibilityId("test-CANCEL");
    private final By continueBtn = AppiumBy.accessibilityId("test-CONTINUE");
    private final By confirmBtn = AppiumBy.accessibilityId("test-CONFIRM");
    private final By finishBtn = AppiumBy.accessibilityId("test-FINISH");
    private final By checkOutCompleteMsg = AppiumBy.androidUIAutomator("new UiSelector().text(\"THANK YOU FOR YOUR ORDER\")");
    private final By basketCheckValue = AppiumBy.xpath("//android.widget.TextView[@text=\"1\"]");
    private static final Logger logger = LoggerFactory.getLogger(CheckOutPage.class);

    public CheckOutPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver,15);
        this.locatorHelper = new LocatorHelper(driver);
    }

    public void fillTheCheckoutFields(String firstName, String lastName, String postalCode) {
        waitHelper.waitForVisibilityOfElement(firstNameInput,15).sendKeys(firstName);
        waitHelper.waitForVisibilityOfElement(lastNameInput,15).sendKeys(lastName);
        waitHelper.waitForVisibilityOfElement(postalCodeInput,15).sendKeys(postalCode);
    }

    public void clickTheCancelButton(){
        waitHelper.waitForVisibilityOfElement(cancelBtn,15).click();
    }

   public void clickTheContinueButton() {
       waitHelper.waitForVisibilityOfElement(continueBtn, 15).click();
   }

    public void clickTheConfirmButton(){
        waitHelper.waitForVisibilityOfElement(confirmBtn,15).click();
    }

    public void clickTheFinishButton(){
          driver.findElement(AppiumBy.androidUIAutomator(
              "new UiScrollable(new UiSelector().scrollable(true))" +
                         ".scrollIntoView(new UiSelector().description(\"test-FINISH\"))"
          ));
        locatorHelper.getFinishButton();
    }

    public String getSuccessMsgOfCheckOut(){
        try {
            logger.info("Attempting to fetch the success message of an order");
            waitHelper.waitForVisibilityOfElement(checkOutCompleteMsg,15).isDisplayed();
            WebElement errorMessageSuccess = waitHelper.waitForVisibilityOfElement(checkOutCompleteMsg, 15);
            String successMessageText = errorMessageSuccess.getText();
            logger.info("Success message of the order successfully fetched: {}", successMessageText);
            return successMessageText;
        } catch (Exception e) {
            logger.error("Error while fetching the error message. Details: {}", e.getMessage(), e);
        }
        return "";
    }
}
