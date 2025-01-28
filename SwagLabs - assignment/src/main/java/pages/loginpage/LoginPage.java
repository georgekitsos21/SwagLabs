package pages.loginpage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitHelper;

public class LoginPage {

    protected final AndroidDriver driver;
    private final WaitHelper waitHelper;
    private final By usernameField = AppiumBy.accessibilityId("test-Username");
    private final By passwordField = AppiumBy.accessibilityId("test-Password");
    private final By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    private final By errorMsg =  AppiumBy.xpath("//*[contains(@text, 'Sorry, this user has been locked out.')]");
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver, 10);
    }

    public void waitForAppToLoad() {
        try {
            waitHelper.waitForVisibilityOfElement(usernameField, 10);
            logger.info("App has loaded successfully, username field is visible.");
        } catch (NoSuchElementException e) {
            logger.error("Failed to load the app: Username field not visible.", e);
        }
    }

    public void fillUserName(String username) {
        try {
            waitHelper.waitForVisibilityOfElement(usernameField, 5).sendKeys(username);
            logger.info("Entered username: {}", username);
        } catch (NoSuchElementException e) {
            logger.error("Unable to find username field to enter: {}", username, e);
        }
    }

    public void fillPassword(String password) {
        try {
            waitHelper.waitForVisibilityOfElement(passwordField, 5).sendKeys(password);
            logger.info("Entered password.");
        } catch (NoSuchElementException e) {
            logger.error("Unable to find password field to enter the password.", e);
        }
    }

    public void clickLoginButton() {
        try {
            waitHelper.waitForVisibilityOfElement(loginButton, 5).click();
            logger.info("Clicked on the login button.");
        } catch (NoSuchElementException e) {
            logger.error("Unable to click on the login button.", e);
        }
    }

    public String getErrorMessageForLockedAcc() {
        try {
            logger.info("Attempting to fetch the error message.");
            waitHelper.waitForPresenceOfElement(errorMsg,5);
            WebElement errorMessageElement = waitHelper.waitForVisibilityOfElement(errorMsg, 5);

            if (errorMessageElement != null && errorMessageElement.isDisplayed()) {
                String errorText = errorMessageElement.getText();
                logger.info("Error message fetched successfully: {}", errorText);
                return errorText;
            } else {
                logger.error("Error message element is not visible or found.");
                return "Error message not found.";
            }
        } catch (Exception e) {
            logger.error("Error while fetching the error message. Details: {}", e.getMessage(), e);
        }
        return "";
    }

    public String getErrorMessageForProblematicAcc() {
        try {
            logger.info("Attempting to fetch the error message for the problematic account");
            waitHelper.waitForVisibilityOfElement(errorMsg,5);
            WebElement errorMessageElementForPrbAcc = waitHelper.waitForVisibilityOfElement(errorMsg, 5);
            String errorText = errorMessageElementForPrbAcc.getText();
            logger.info("Error message fetched successfully for the problematic account: {}", errorText);
            return errorText;
        } catch (Exception e) {
            logger.error("Error while fetching the error message. Details: {}", e.getMessage(), e);
        }
        return "";
    }
}