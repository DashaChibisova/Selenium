Feature: Check how work cart


  Scenario: Buy on main page product

    Given User is on main page
    When User  select a product click on it and
    And add it to the cart on the product page
    And returns to the main page
    And goes to Checkout
    And user removes all items from the shopping cart//действия


    Then Cart should be empty  a//проверки