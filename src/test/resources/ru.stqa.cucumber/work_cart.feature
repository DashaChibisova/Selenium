Feature: Check how work cart


  Scenario: Buy product on main page

    Given User is on main page
    When User add the selected products to the cart '3' times
    And user removes all items from the shopping cart

    Then Cart should be empty