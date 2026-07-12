@api @negative
Feature: Cancel rules
  Scenario: A placed order can only be cancelled once
    Given "alice" is logged in
    And "alice" has a PLACED order
    When she cancels the order
    Then the response status is 200
    When she cancels the order
    Then the response status is 409
