@Regression

Feature: User importing a URL to Web view option
  As a user
  I want to log in to my account
  Open menu and go to web view sub option

  Scenario: Successful importing of URL to Web view menu option
    When I click the Web view menu option
    And I add a URL to the field
    And I click the go to site button
    Then I should be able to redirect to the particular URL

  Scenario: Unsuccessful importing of URL to Web view menu option
    When I click the Web view menu option
    And I add an invalid URL to the field
    And I click the go to site button
    Then I should see an error message about the invalid URL