@Login @Smoke @Regression

Feature: User Login
  As a user
  I want to log in to my account
  So that I can access the application features

  @SuccessfullyLogin @Regression @Smoke
  Scenario: Successful login with valid credentials
    When I launch the app and I use the standard user
    And I click the log in button
    Then I should be successfully logged in and I should see the product header

  @ErrorMessage @Smoke
  Scenario: Successful login with invalid credentials
    When I launch the app and I use the locked user
    And I click the log in button
    When I should not be successfully logged in and I should not be able to see the product header
    Then I should see a an error message about the locked account

  @ErrorMessage @Smoke
  Scenario: Successful login with invalid credentials - problematic account
    When I launch the app and I use the problematic user
    And I click the log in button
    When I should not be successfully logged in and I should not be able to see the product header
    Then I should see a an error message about the problematic account
