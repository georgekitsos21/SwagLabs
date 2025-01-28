@Regression @Smoke

Feature: User add product to the basket and goes to checkout page.

  @Smoke
  Scenario: Remove the selection of the second product
    When I click to menu
    And I choose the all items option
    And I click to the second product
    And I scroll down and click to the add to card button
    And I click to the basket in order to see my second product selected and the number 1 of it
    Then I should see the remove button
    When I click to remove button and the product should be disappeared from the list
    Then I click to the basket in order to see my selected product and the number -1 as a basket value

  Scenario: Check buy process of the 2nd product with proper basket value
    When I click to menu
    And I choose the all items option
    And I click to the second product
    And I scroll down and click to the add to card button
    Then I should see the remove button
    And I click to the basket in order to see my selected product and the number 1 as a basket value
    When I click to checkout button
    And I fill the required fields anc click the finish button
    And I should see the successful message of my purchase

  Scenario: Check buy process of the 3nd product with not proper basket value
      When I click to menu
      And I choose the all items option
      And I click to the third product
      And I scroll down and click to the add to card button
      Then I should see the remove button
      And I click to the basket in order to see my second product and the number 2 as a basket value

  Scenario: Select the second product and the go back to all products
    When I click to menu
    And I choose the all items option
    And I click to the second product
    When I click the back to products button
    Then I should be able to see the second product

  Scenario: Verify sorting
    When I click to menu
    And I choose the all items option
    And I click to sorting icon
    Then I see the price with "7.99" should be sorted as first