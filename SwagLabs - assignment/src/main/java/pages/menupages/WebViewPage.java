package pages.menupages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitHelper;

public class WebViewPage {
    protected final AndroidDriver driver;
    private final WaitHelper waitHelper;
    private final By webViewElement = AppiumBy.accessibilityId("test-WEBVIEW");
    private final By urlInput = AppiumBy.xpath("//android.widget.EditText[@content-desc='test-enter a https url here...']");
    private final By goToSiteBtn = AppiumBy.accessibilityId("test-GO TO SITE");
    private final By menuIcon = AppiumBy.accessibilityId("test-Menu");
    private final By gmailWebView = new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Gmail\")");
    private final By errorMessageForInvalidUrl = new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Please provide a correct https url\")");
    private static final Logger logger = LoggerFactory.getLogger(WebViewPage.class);


    public WebViewPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 5);
    }

    public void clickWebViewOption() {
        try {
            logger.info("Attempting to open the WebView option...");
            waitHelper.waitForVisibilityOfElement(menuIcon, 15).click();
            logger.info("Menu icon clicked successfully.");

            waitHelper.waitForVisibilityOfElement(webViewElement, 5).click();
            logger.info("WebView option clicked successfully.");
        } catch (Exception e) {
            logger.error("Failed to open the WebView option: ", e);
        }
    }

    public void addUrlToImportField(String url) {
        try {
            logger.info("Attempting to enter URL: {}", url);
            waitHelper.waitForPresenceOfElement(urlInput, 10);
            waitHelper.waitForVisibilityOfElement(urlInput, 10).sendKeys(url);
            logger.info("URL entered successfully.");
        } catch (Exception e) {
            logger.error("Failed to enter the URL: ", e);
        }
    }

    public void clickGoToSiteBtn() {
        try {
            logger.info("Attempting to click the 'Go To Site' button...");
            driver.findElement(goToSiteBtn).click();
            logger.info("'Go To Site' button clicked successfully.");
        } catch (Exception e) {
            logger.error("Failed to click the 'Go To Site' button: ", e);
        }
    }

    public boolean isUrlTheExpected() {

        try {
            logger.info("Checking if the expected WebView element is visible...");

            boolean isVisible = waitHelper.waitForVisibilityOfElement(gmailWebView, 15).isDisplayed();

            if (isVisible) {
                logger.info("The WebView element is visible.");
            } else {
                logger.warn("The WebView element is not visible.");
            }

            return isVisible;
        } catch (Exception e) {
            logger.error("An error occurred while checking the WebView element visibility: ", e);
            return false;
        }
    }

    public String getErrorMessageForInvalidUrl() {
        try {
            logger.info("Attempting to fetch the error message for the problematic account");
            waitHelper.waitForVisibilityOfElement(errorMessageForInvalidUrl,5);
            WebElement errorMessageElementForPrbAcc = waitHelper.waitForVisibilityOfElement(errorMessageForInvalidUrl, 5);
            String errorText = errorMessageElementForPrbAcc.getText();
            logger.info("Error message fetched successfully for the i: {}", errorText);
            return errorText;
        } catch (Exception e) {
            logger.error("Error while fetching the error message. Details: {}", e.getMessage(), e);
        }
        return "";
    }
}