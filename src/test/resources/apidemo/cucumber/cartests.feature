Feature: Cars CRUD operations

  Scenario: Getting all cars
    Given The endpoint for "cars" is available for method "GET"
    When I retrieve all cars
    Then I should receive all cars

  Scenario: Create a car
    Given The endpoint for "cars" is available for method "POST"
    When I create a car with brand "Toyota" and license plate "CD4567" with weight 1600 and owner id 1
    Then The response status is 201
    And The car ID is 2
