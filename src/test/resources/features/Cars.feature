Feature: Cars Make Search
  As a user
  I want to search cars based on make option
  So that I can verify cars make and search results count.

  Background:
    Given I am on the trademe webpage to choose the motors search option
    And I go to the Cars search page under "Motors"
    When I click Make dropdown

  @WebTest
  Scenario: To verify the number of named car makes count in Make dropdown
    Then I can verify number of named car makes count in the dropdown

  @WebTest
  Scenario: To verify cars results count based on make search
    And I select make option
      | Ferrari |
      | BMW     |
      | Mazda   |
      | Honda   |
    And I click Search button
    Then I can see the number of cars search results count