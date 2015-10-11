Feature: Inicial
  As a user,
  I want to build up my own two stories high cake.

  Scenario: Simple cake building

    Given I am a user with an empty cart
    When I order a two stories high cake
    Then I should enter the base filling
    Then I should enter a new floor
