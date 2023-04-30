Feature: Car controller
  We test all CRUD operations

  Scenario: Getting all cars
    When I retrieve all cars
    Then I should receive all cars