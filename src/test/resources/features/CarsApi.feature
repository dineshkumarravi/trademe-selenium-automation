Feature: Cars Make Search
  As a user
  I want to look for list of cars are available in Trade Me Api
  So that I can verify number of named car makes count.

  Background:
    Given I am on the trademe webpage to choose the motors search option
    And I go to the Cars search page under "Motors"
    When I click Make dropdown

  @APITest
  Scenario: To verify the number of named car makes count using Trade Me Api
    And I fetch the car make details by sending api request
    Then the response will return status as 200
    And I can verify number of named car makes count