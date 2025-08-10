Feature: User Download Invoice after purchase order

  Scenario: user can Download Invoice successfully after purchase order
    Given user navigate to Login page
    When user can Enter correct email address and password
    And user click on login button
    And user open cart details
    And user click on Proceed To Checkout button
    And user enter Comment to the order and click on Place order
    And user can Enter payment details
    And user can click on pay and Confirm order Button
    Then user can download the Invoice

  Scenario:
    Given user navigate to Login page
    When user can Enter correct email address and password
    And user click on login button
    And user open cart details
    Then user cannot continue
