@Search
Feature: Demoblaze

  Scenario: Demoblaze confirm price
    Given customer is on "https://www.demoblaze.com/index.html"
    When customer navigates to "Laptops"
    And Add to cart product "Sony vaio i5"
    And Add to cart second product "Dell i7 8gb"
    And Delete "Dell i7 8gb" from cart
    And Proceed for Place order
    And Fill all details
    And Complete the order
    Then Order amount should be equal to product price
    And Click on Ok
