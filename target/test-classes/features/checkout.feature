@checkout
Feature: Checkout Functionality

  Background: Login with valid credentials
    Given user login with test data "validUser"
    And user clicks login button
    Then user should be logged in successfully
    And user is on product page
    Given user will add one item to cart "productName"
    When user click on the cart icon
    Then user will navigate to cart page
    And added "productName" should be present in cart
    When user click checkout button to proceed
    Then user will navigate to checkout page

  Scenario: Attempt checkout with empty form
    When user submits the checkout form
    Then error message should be "First Name is required"
    When user clicks on menu button
    And user clicks logout
    Then user will be logged out successfully

  Scenario: Attempt checkout with missing last name
    When user enters checkout details "checkoutDetails" with missing lastName
    And user submits the checkout form
    Then error message should be "Last Name is required"
    When user clicks on menu button
    And user clicks logout
    Then user will be logged out successfully

  Scenario: Attempt checkout with missing postal code
    When user enters checkout details "checkoutDetails" with missing postalCode
    And user submits the checkout form
    Then error message should be "Postal Code is required"
    When user clicks on menu button
    And user clicks logout
    Then user will be logged out successfully

  Scenario: Successful checkout with valid details
    When user enters valid checkout details "checkoutDetails"
    And user submits the checkout form
    Then user should be navigated to the overview screen
    When user finishes the checkout
    Then user should be navigated to the checkout complete screen
    And confirmation message should be "THANK YOU FOR YOU ORDER"
    When user clicks on menu button
    And user clicks logout
    Then user will be logged out successfully
