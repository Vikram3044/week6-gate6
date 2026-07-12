@api @db
Feature: Cart totals
  Scenario: Cart total is the sum of quantity times price
    Given "alice" is logged in
    And "alice" has a cart with 2 x "SKU-BAG"
    When she requests her cart
    Then its totalPaise is 99800
