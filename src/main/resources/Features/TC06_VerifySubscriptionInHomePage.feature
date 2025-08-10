Feature: Verify Subscription In Home Page

  Scenario: user can Verify Subscription In Home Page successfully
    Given user open home page
    When user enter email to subscribe
    Then user can Click subscribe button

  Scenario: user cannot verify subscription in home page
    Given user open home page
    When user enter non Existing email
    Then user cannot verify subscription

