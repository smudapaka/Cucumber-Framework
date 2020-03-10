Feature: My Synovus Application

  @Smoke
  Scenario: Login into My Synovus Application
    Given Launch My Synovus
    When Click Personal
    When Click Charge
    Then Verify Promotional Offer
