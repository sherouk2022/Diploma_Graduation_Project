// Feature file: UserRegistration.feature
Feature: User Registration

  Scenario: Successful user registration
    Given navigate the user to Signin page
    When user can enter Name
    And user can enter new Email and click the Signup button
    And user can Fill First details
    And user Select checkbox sign up for our newsletter
    And user Select checkbox Receive special offers from our partners
    And user can Fill Address Information
    And user click on Create Account button
    Then navigate the user to home page

  Scenario: Invalid Registration
    Given navigate the user to Signin page
    When user can enter Name
    And User Enter an Existing Email and enter Signup button
    Then user cannot navigate to the next page
