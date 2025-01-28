package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class WaitHelper {

    protected final WebDriverWait wait;
    protected final AndroidDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(WaitHelper.class);


    public WaitHelper(AndroidDriver driver, int defaultTimeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(defaultTimeoutInSeconds));
    }

    public WebElement waitForVisibilityOfElement(By locator, int timeoutInSeconds) {
        WebDriverWait waiForElementVisibility = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return waiForElementVisibility.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForPresenceOfElement(By locator, int timeoutInSeconds) {
        WebDriverWait waiForPresenceElementVisibility = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        waiForPresenceElementVisibility.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public Alert waitForAlert(int timeout) {
        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return alertWait.until(ExpectedConditions.alertIsPresent());
    }
}