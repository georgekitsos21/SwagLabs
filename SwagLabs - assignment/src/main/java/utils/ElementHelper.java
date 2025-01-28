package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementHelper {
    private static final Logger logger = LoggerFactory.getLogger(ElementHelper.class);
    private final AppiumDriver driver;

    public ElementHelper(AppiumDriver driver) {
        this.driver = driver;
    }

    public String getElementText(By elementLocator) {
        try {
            logger.info("Attempting to fetch the text of the element located by: {}", elementLocator);
            WebElement element = driver.findElement(elementLocator);
            String elementText = element.getText();
            logger.info("Text fetched successfully: {}", elementText);
            return elementText;
        } catch (Exception e) {
            logger.error("Error while fetching the element text. Details: {}", e.getMessage(), e);
        }
        return "";
    }
}