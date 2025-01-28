package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class ProductsSteps extends BaseTest {

    @When("I click to menu")
    public void clickToMenu() {
        productsPage.clickMenu();
    }

    @When("I choose the all items option")
    public void clickToAllItems() {
        productsPage.clickAllItemsOption();
    }

    @When("I click to the second product")
    public void clickToTheSecondProduct() {
        productsPage.verifyAndSelectSecondProduct();
    }

    @When("I scroll down and click to the add to card button")
    public void scrollToAddCardBtn() throws InterruptedException {
        secondProductPage.scrollToAddToCartAndClick();
    }

    @Then("I should see the remove button")
    public void checkRemoveButtonVisibility() {
        boolean actualRemoveBtnVisibility = secondProductPage.checkRemoveVisibility();
        assertTrue("The remove button should be visible.", actualRemoveBtnVisibility);
    }

    @When("I click to remove button and the product should be disappeared from the list")
    public void removeProductFromTheList() {
        secondProductPage.clickRemoveBtn();
    }

    @Then("I click to the basket in order to see my selected product and the number {int} as a basket value")
    public void checkTheUpdatedValueInTheBasket(int valueOfTheBasket) {
        int expectedValue = secondProductPage.checkTheValueOfTheBasket(valueOfTheBasket);
        assertEquals("The value of the basket is not the expected", expectedValue, valueOfTheBasket);
    }

    @Then("I click to the basket in order to see my second product and the number {int} as a basket value")
    public void checkTheUpdatedBasketValue(int valueOfTheBasket) {
        int expectedValue = secondProductPage.checkTheValueOfTheBasket(valueOfTheBasket);
        assertNotEquals("The basket value should not be equal to the expected value!", expectedValue, valueOfTheBasket);
    }

    @When("I click to checkout button")
    public void clickToCheckOut() {
        secondProductPage.goToCheckOut();
    }

    @When("I fill the required fields anc click the finish button")
    public void fillTheRequiredFields() throws InterruptedException {
        secondProductPage.completeThePurchaseOfTheProduct("george", "kitsos", "12345");
    }

    @Then("I should see the successful message of my purchase")
    public void checkTheSuccessfulMsgOfThePurchase() {
        String expectedSuccessMg = "THANK YOU FOR YOU ORDER";
        String actualSuccessMsg = checkOutPage.getSuccessMsgOfCheckOut();
        assertEquals("The success message of the checkout is not the expected", expectedSuccessMg, actualSuccessMsg);
    }

    @When("I click to the third product")
    public void clickToTheThirdProduct() {
        thirdProductsPage.clickToTheThirdProduct();
    }

    @When("I click to the first product")
    public void clickToTheFistProduct() {
        firstProductsPage.clickToTheFirstProduct();
    }

    @Then("I click to the basket in order to see my second product selected and the number {int} of it")
    public void checkTheUpdatedValueInTheBasketForTheSecondProduct(int valueOfTheBasket) {
        secondProductPage.clickRemoveBtn();
        int expectedValue = firstProductsPage.checkTheValueOfTheBasketForTheSecondProduct(valueOfTheBasket);
        assertEquals("The value of the basket is not the expected", expectedValue, valueOfTheBasket);
    }

    @When("I click the back to products button")
    public void clickToBackToAllProducts() {
        secondProductPage.clickToBackToProductsBtn();
    }

    @Then("I should be able to see the second product")
    public void checkVisibilityOfTheProd() {
        assertTrue("The second product is not visible", secondProductPage.checkVisibilityOfTheSecProd());
    }

    @When("I click to sorting icon")
    public void clickToSortingIcon() {
        productsPage.clickSortingIcon();
    }

    @Then("I see the price with {string} should be sorted as first")
    public void checkSortingLowToHigh(String expectedLowestPrice){
        boolean isLowestPriceCorrect = productsPage.isFirstProductPriceCorrect(expectedLowestPrice);
        assertTrue("The first product's price is not correct.", isLowestPriceCorrect);
    }
}


