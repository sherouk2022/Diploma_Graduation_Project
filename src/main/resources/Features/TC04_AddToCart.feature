Feature: user Add Products To the Cart Successfully

  Scenario: user can add more than one product to the cart successfully
    Given User open products' page
    When user can select more than product and add them to the cart
    And user can view the Cart
    Then Verify the Products' prices, quantity and total price

