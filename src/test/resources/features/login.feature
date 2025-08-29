@login
Feature: Login Functionality

  Background:
    Given the app is launched and user is on login screen

  Scenario: Login with invalid credentials
    Given user login with test data "invalidUser"
    When user clicks login button
    Then user should see an error message for invalid user

  Scenario: Login with blank userName
    Given user login with no userName "blankUserName" and password
    When user clicks login button
    Then user should see an error message for blank userName

  Scenario: Login with blank password
    Given user login with username "blankPassword" and no password
    When user clicks login button
    Then user should see an error message for blank password

  Scenario Outline: Login with valid credentials
    Given user login with "<validUser>" and "<validPassword>"
    And user clicks login button
    Then user should be logged in successfully
    When user clicks on menu button
    And user clicks logout
    Then user will be logged out successfully
    Examples:
      |validUser||validPassword|
      |standard_user||secret_sauce|
      |problem_user||secret_sauce|