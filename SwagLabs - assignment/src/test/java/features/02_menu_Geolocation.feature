@Regression @Smoke

Feature: Geolocation Functionality
  As a user
  I want to log in to my account and check my Geolocation
  amd I should be able to see the Longitude & Latitude values

  Scenario: As a User, I should be able to see the proper values in the Geolocation menu option
    And I click the Geolocation menu option
    Then I should see Longitude value equals to "37.4219983"
    And I should see Latitude value equals to "-122.084"
