package pages.loginpage;

import enums.Buttons;
import helpers.SharedFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page_objects.BasePage;

public class CallFeaturesPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CallFeaturesPage.class);

    /* Call-Features Page Locators */
    private final By ttyOffBtn = By.cssSelector("#tty-button .svg-icon-tty-off");
    private final By ttyOnBtn = By.cssSelector("#tty-button .svg-icon-tty-on");
    private final By parkBtn = By.id("park-button");
    private final By unparkBtn = By.id("unpark-button");
    private final By printCallTicketBtn = By.id("print-call-ticket-button");
    private final By deleteAbandonedCallBtn = By.id("delete-abandonedCall-ticket-button");
    private final By joinCallBtn = By.id("join-call-button");
    private final By listenCallBtn = By.id("listen-call-button");

    public CallFeaturesPage(WebDriver driver) {
        this.driver = driver;
        sharedFunctions = new SharedFunctions(this.driver);
    }

    /* Call-Features Page functions */
    public void clickButton(Buttons button) {
        switch (button) {
            case TTY_OFF:
                sharedFunctions.click(ttyOffBtn);
                break;
            case TTY_ON:
                sharedFunctions.click(ttyOnBtn);
                break;
            case UNPARK:
                sharedFunctions.click(unparkBtn);
                break;
            case JOIN:
                sharedFunctions.click(joinCallBtn);
                break;
            case LISTEN:
                sharedFunctions.click(listenCallBtn);
                break;
            case REPORT:
                sharedFunctions.click(printCallTicketBtn);
                break;
            case DELETE:
                sharedFunctions.click(deleteAbandonedCallBtn);
                break;
            default:
                logger.error("Wrong parameter passed in function clickButton");
        }
    }

    public boolean buttonIsDisplayed(Buttons button, boolean state) {
        switch (button) {
            case TTY_OFF:
                return sharedFunctions.checkElementVisibility(ttyOffBtn, state);
            case TTY_ON:
                return sharedFunctions.checkElementVisibility(ttyOnBtn, state);
            case UNPARK:
                return sharedFunctions.checkElementVisibility(unparkBtn, state);
            case JOIN:
                return sharedFunctions.checkElementVisibility(joinCallBtn, state);
            case LISTEN:
                return sharedFunctions.checkElementVisibility(listenCallBtn, state);
            case REPORT:
                return sharedFunctions.checkElementVisibility(printCallTicketBtn, state);
            case DELETE:
                return sharedFunctions.checkElementVisibility(deleteAbandonedCallBtn, state);
            default:
                logger.error("Wrong parameters passed in function buttonIsDisplayed");
                return false;
        }
    }

    public String getListenBackgroundColor() {
        return driver.findElement(listenCallBtn).getCssValue("background-color");
    }
}
