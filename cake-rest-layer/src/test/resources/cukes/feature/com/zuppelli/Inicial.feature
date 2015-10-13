Feature: Inicial
  As a user,
  I want to build up my own two stories high cake.

  Scenario: Simple cake building

    Given I am a user with an empty cart
    When I order a two stories high cake
    Then I should enter the base filling
    Then I should enter a new floor

  Scenario Outline: Price test
    Given I am a user using '<cobertura>' price '<cobertura_precio>' and '<relleno>' price '<relleno_precio>' and '<per_kilo>'
    When I order a '<kilo>' cake
    Then I should have to pay '<precio>'

  Examples:
    |    cobertura      | cobertura_precio |       relleno     | relleno_precio   | kilo | per_kilo |   precio |
    | test_chispas      |         15       | test_chocolate    |        20        |  2   |   30     |    95    |
