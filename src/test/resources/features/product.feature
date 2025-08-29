@product
Feature: Product Functionality

  Background: Login with valid credentials
    Given user login with test data "validUser"
    And user clicks login button
    Then user should be logged in successfully

  Scenario: Verify all product items have image, title, and price
    When user is on product page
    Then user confirms all items are loaded properly
    When user clicks on menu button
    And user clicks logout
    Then user will be logged out successfully

  Scenario: Verify product detail view
    Given user is on product page
    When user taps on a product "productName"
    Then user should see the product detail screen with item name "productName"
    When user clicks on menu button
    And user clicks logout
    Then user will be logged out successfully

  Scenario: Add single item to cart and remove it
    When user will add one item to cart "productName"
    Then cart icon will update to show "1"
    When user remove the added item "productName"
    Then cart icon will not have any value
    When user clicks on menu button
    And user clicks logout
    Then user will be logged out successfully

  Scenario: Add and remove multiple items from cart
    When the user adds the following items to the cart:
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |
    When the user removes the same items from the cart:
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |
    Then cart icon will not have any value
    When user clicks on menu button
    And user clicks logout
    Then user will be logged out successfully


