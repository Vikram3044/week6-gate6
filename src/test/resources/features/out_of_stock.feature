@api @negative
Feature: Out of stock inventory
  Scenario: Stock cannot be exceeded
    Given "alice" is logged in
    And "alice" has a cart with 1 x "SKU-BAG"
    When she adds 1 x "SKU-CAP" to her cart
    Then the response status is 409
