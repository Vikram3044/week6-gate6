@smoke @ui @api
Feature: Product search
  Scenario: Search returns matching products in UI and API
    Given "alice" signs into the web shop
    Then the home page shows "Metro Carryall"
    When the catalogue is searched for "Carryall"
    Then the API response contains "Metro Carryall"
