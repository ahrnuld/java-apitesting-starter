Feature: Car controller
  We test all CRUD operations

  Scenario: Getting all cars
    Given The endpoint for "cars" is available for method "GET"
    When I retrieve all cars
    Then I should receive all cars