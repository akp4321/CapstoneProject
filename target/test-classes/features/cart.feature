@cart
Feature: Cart Functionality

  Background: Login with valid credentials
    Given user login with test data "validUser"
    And user clicks login button
    Then user should be logged in successfully
    And user is on product page

  Scenario: Verify added product is displayed on cart page
    Given user will add one item to cart "productName"
    When user click on the cart icon
    Then user will navigate to cart page
    And added "productName" should be present in cart
    When user clicks continue to shopping button
    Then user is on product page
    When user clicks on menu button
    And user clicks logout
    Then user will be logged out successfully
  @prod
  Scenario: Validate multiple products,quantities, and prices of each product in cart
    Given the user adds the following items to the cart:
      | Sauce Labs Bike Light     |
      | Sauce Labs Bolt T-Shirt   |
    When user click on the cart icon
    Then user will navigate to cart page
    And the cart should contain the following items:
      | Product Name              | Quantity | Price   |
      | Sauce Labs Bike Light     | 1        | $9.99   |
      | Sauce Labs Bolt T-Shirt   | 1        | $15.99  |
    When user clicks continue to shopping button
    Then user is on product page
    When user clicks on menu button
    And user clicks logout
    Then user will be logged out successfully

