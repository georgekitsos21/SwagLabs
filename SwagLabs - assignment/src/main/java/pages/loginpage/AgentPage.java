package pages.loginpage;

import helpers.SharedFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page_objects.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AgentPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(AgentPage.class);

    /* Agent Page Locators */
    private final By agentTab = By.cssSelector("[data-autotest='agents']");
    private final By agentsTable = By.cssSelector("app-agents-list table");
    private final By agentTableHeader = By.cssSelector("app-agents-list table thead th .mat-sort-header-container");
    private final By agentSortBtn = By.cssSelector("app-agents-list table .mat-sort-header-arrow");
    private final By agentTableFilter = By.cssSelector("app-agents-list table thead input[type='text']");
    private final By agentTableRows = By.cssSelector("app-agents-list tbody tr");
    private final By agentTableColumn = By.cssSelector("app-agents-list tbody [data-autotest='Agent']");
    private final By positionTableColumn = By.cssSelector("app-agents-list tbody [data-autotest='Position']");
    private final By statusTableColumn = By.cssSelector("app-agents-list tbody [data-autotest='Status']");
    private final By clearSearchField = By.cssSelector("app-agents-list [aria-label='clear text']");
    private final By listenHeaderElement = By.cssSelector("[data-autotest='Listen'][mat-header-cell='']");
    private final By listenBtnMonitorAgent = By.cssSelector("app-action-cell button.listen");
    private final By joinBtnMonitorAgent = By.cssSelector("app-action-cell button.join");

    public AgentPage(WebDriver driver) {
        this.driver = driver;
        sharedFunctions = new SharedFunctions(this.driver);
    }

    /* Agent Page functions */
    public void clickAgentTab() {
        sharedFunctions.click(agentTab);
    }

    public void clickClearSearchField() {
        sharedFunctions.click(clearSearchField);
    }

    public boolean agentsTableIsDisplayed(boolean state) {
        return sharedFunctions.checkElementVisibility(agentsTable, state);
    }

    public boolean verifyListenHeaderVisibility(String expectedState) {
        boolean shouldBeVisible = expectedState.equalsIgnoreCase("is");

        try {
            boolean isVisible = sharedFunctions.checkElementVisibility(listenHeaderElement, true);
            logger.info("The listen button is visible: {}", isVisible);

            return isVisible == shouldBeVisible;
        } catch (NoSuchElementException e) {
            logger.warn("The listen button is not present in the DOM: {}", e.getMessage());

            return !shouldBeVisible;
        } catch (Exception e) {
            logger.warn("An error occurred while checking visibility: {}", e.getMessage());

            return !shouldBeVisible;
        }
    }

    public boolean verifyListenCircleVisibility(String expectedState) {
        boolean shouldBeVisible = expectedState.equalsIgnoreCase("is not");

        try {
            boolean isVisible = sharedFunctions.checkElementVisibility(listenHeaderElement, true);
            logger.info("The listen circle is visible: {}", isVisible);

            return isVisible == shouldBeVisible;
        } catch (NoSuchElementException e) {
            logger.warn("The listen circe is not present in the DOM: {}", e.getMessage());

            return !shouldBeVisible;
        } catch (Exception e) {
            logger.warn("An error occurred while checking listen button visibility: {}", e.getMessage());

            return !shouldBeVisible;
        }
    }


    public void clickJoinBtnMonitorAgent(int row) {
        sharedFunctions.checkElementVisibilityAfterTime(joinBtnMonitorAgent,true,10);
        sharedFunctions.findElements(joinBtnMonitorAgent).get(row).click();
    }


    public void checkListenBtnVisibility() {
        sharedFunctions.checkElementVisibilityAfterTime(listenBtnMonitorAgent,true,10);
    }

    public void clickListenBtnMonitorAgent(int row) {
        sharedFunctions.checkElementVisibilityAfterTime(listenBtnMonitorAgent,true,10);
        sharedFunctions.findElements(listenBtnMonitorAgent).get(row).click();
    }

    public void doubleClickToListenBtn(){
        sharedFunctions.doubleClick(driver.findElement(listenBtnMonitorAgent));
    }

    public boolean sortArrowIsDisplayed(String column) {
        String cssStyle;
        switch (column) {
            case "AGENT":
                cssStyle = driver.findElements(agentTableHeader).get(0)
                        .findElement(By.className("mat-sort-header-arrow")).getAttribute("style");
                return cssStyle.contains("opacity: 1;");
            default:
                logger.error("Wrong parameters passed in function sortArrowIsDisplayed");
                return false;
        }
    }

    public boolean OrderSortArrowIsDisplayed(String column, String order) throws InterruptedException {
        if (!column.equals("AGENT")) {
            logger.error("Unsupported column passed in function OrderSortArrowIsDisplayed: {}", column);
            return false;
        }

        List<WebElement> elements = driver.findElements(agentSortBtn);

        if (elements.isEmpty()) {
            logger.error("No elements found with the given agentSortBtn locator.");
            return false;
        } else {
            WebElement sortArrow = elements.get(0);
            Thread.sleep(1000);
            sortArrow.click();
            return true;
        }
    }

    public boolean getAgentValue(String column, String expectedValue, int row) {
        switch (column) {
            case "AGENT":
                return sharedFunctions.waitAttributeToHaveValue(agentTableColumn, row, "innerText", expectedValue, 3);
            case "POSITION":
                return sharedFunctions.waitAttributeToHaveValue(positionTableColumn, row, "innerText", expectedValue, 3);
            case "STATUS":
                return sharedFunctions.waitAttributeToHaveValue(statusTableColumn, row, "innerText", expectedValue, 3);
            default:
                logger.error("Wrong parameters passed in function getAgentValue");
                return false;
        }
    }

    public List<String> getAgentTableHeaders() {
        List<WebElement> elements = driver.findElements(agentTableHeader);
        List<String> agentHeaders = new ArrayList<>();
        for (WebElement el : elements) {
            agentHeaders.add(el.getText());
        }
        return agentHeaders;
    }

    public int getAgentsTableNumberOfRows() {
        return sharedFunctions.findElements(agentTableRows).size();
    }

    public void findAgentBy(String column, String inputValue) {
        WebElement inputField;
        switch (column) {
            case "AGENT":
                inputField = driver.findElements(agentTableFilter).get(0);
                sharedFunctions.sendKeys(inputField, inputValue);
                break;
            case "POSITION":
                inputField = driver.findElements(agentTableFilter).get(1);
                sharedFunctions.sendKeys(inputField, inputValue);
                break;
            case "STATUS":
                inputField = driver.findElements(agentTableFilter).get(2);
                sharedFunctions.sendKeys(inputField, inputValue);
                break;

            default:
                logger.error("Wrong parameters passed in function findAgentBy");
        }
        sharedFunctions.checkElementVisibility(driver.findElements(agentTableRows).get(0), true);
    }

    public String getFilterInputValue(String column) {
        switch (column) {
            case "AGENT":
                return driver.findElements(agentTableFilter).get(0).getAttribute("value");
            case "POSITION":
                return driver.findElements(agentTableFilter).get(1).getAttribute("value");
            case "STATUS":
                return driver.findElements(agentTableFilter).get(2).getAttribute("value");
            default:
                logger.error("Wrong parameters passed in function getFilterInputValue");
                return null;
        }
    }

    public void sortBy(String column) {
        switch (column) {
            case "AGENT":
                sharedFunctions.click(agentTableHeader, 0);
                break;
            default:
                logger.error("Wrong parameters passed in function sortBy");
        }
    }

    public int compareColumnRows(int row1, int row2, String column, String order) {
        if (column.equals("AGENT")) {
            String name1 = driver.findElements(agentTableColumn).get(row1).getText();
            String name2 = driver.findElements(agentTableColumn).get(row2).getText();

            if (order.equalsIgnoreCase("ascending") || order.equalsIgnoreCase("descending")){
                return name1.compareToIgnoreCase(name2);
            } else {
                logger.error("Invalid order parameter. User 'ascending' or 'descending'. ");
            }
        }
        logger.error("Wrong parameters passed in function compareColumnRows");
        return 0;
    }

    public int checkAgentsTableFiltersSize() {
        return driver.findElements(agentTableFilter).size();
    }

    public int getRowIndexWithGivenAgentNameValue(String agent) {
        try {
            List<String> agentsCells = sharedFunctions.getTextFromElements(agentTableColumn);
            int index = 0;
            for (String agentCell : agentsCells) {
                try {
                    logger.info("agent name {} in row {}", agentCell, index);
                    if (agentCell.contains(agent)) {
                        logger.info("telephone value {} found in row {}", agentCell, index);
                        return index;
                    } else {
                        index++;
                    }
                } catch (Exception e) {
                    logger.error("ERROR on loop of getRowIndexWithGivenAgentNameValue: {}", e.getMessage());
                }
            }
            return -1;
        } catch (Exception e) {
            logger.error("Exception in getRowIndexWithGivenAgentNameValue");
            return -1;
        }
    }

    public boolean waitAgentColumnText(String agentColumn, int row, String expectedValue) {
        By locator;
        switch (agentColumn) {
            case "Agent":
                locator = agentTableColumn;
                break;
            case "Position":
                locator = positionTableColumn;
                break;
            case "Status":
                locator = statusTableColumn;
                break;
            default:
                logger.error("Wrong parameters passed in function waitAgentColumnText");
                locator = null;
        }
        try {
            return sharedFunctions.waitForTextInElementInRow(locator, row, expectedValue);
        } catch (Exception e) {
            logger.error("Exception in waitAgentColumnText");
            return false;
        }
    }
}
