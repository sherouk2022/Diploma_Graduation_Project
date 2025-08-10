Feature: Login User

  Scenario: Valid Login for User
    Given user can Click on Signup or Login button
    When user can Enter correct email address and password
    Then user click on login button

  Scenario: Invalid Login for user
    Given user can Click on Signup or Login button
    Then user can Enter Incorrect email address and password and click on login button

