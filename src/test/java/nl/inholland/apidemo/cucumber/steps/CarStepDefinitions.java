package nl.inholland.apidemo.cucumber.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nl.inholland.apidemo.models.Car;
import nl.inholland.apidemo.models.DTO.CarDTO;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

public class CarStepDefinitions extends BaseStepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    private final HttpHeaders httpHeaders = new HttpHeaders();

    private ResponseEntity<String> response;
    @Autowired
    private ObjectMapper mapper;

    @Given("The endpoint for {string} is available for method {string}")
    public void theEndpointForIsAvailable(String endpoint, String method) {
        response = restTemplate
                .exchange("/" + endpoint,
                        HttpMethod.OPTIONS,
                        new HttpEntity<>(null, httpHeaders), // null because OPTIONS does not have a body
                        String.class);
        List<String> options = Arrays.stream(response.getHeaders()
                        .get("Allow")
                        .get(0)// The first element is all allowed methods separated by comma
                        .split(","))
                .toList();
        Assertions.assertTrue(options.contains(method.toUpperCase()));
    }

    @When("^I retrieve all cars$")
    public void i_retrieve_all_cars() {

        response = restTemplate.exchange(restTemplate.getRootUri() + "/cars", HttpMethod.GET, new HttpEntity<>(null, new HttpHeaders()), String.class);
    }

    @Then("I should receive all cars")
    public void i_should_receive_all_cars() {

        int actual = JsonPath.read(response.getBody(), "$.size()");
        Assertions.assertEquals(1, actual);
    }

    @When("I create a car with brand {string} and license plate {string} with weight {int} and owner id {int}")
    public void iCreateACarWithBrandAndLicensePlateWithWeight(String brand, String licensePlate, int weight, long id) throws JsonProcessingException {
        CarDTO dto = createCarDto(brand, licensePlate, weight, id);
        httpHeaders.add("Content-Type", "application/json");
        response = restTemplate.exchange("/cars",
                HttpMethod.POST,
                new HttpEntity<>(
                        mapper.writeValueAsString(dto),
                        httpHeaders
                ), String.class);
    }

    private CarDTO createCarDto(String brand, String licensePlate, int weight, long ownerId) {
        CarDTO dto = new CarDTO();
        dto.setBrand(brand);
        dto.setLicensePlate(licensePlate);
        dto.setWeight(weight);
        dto.setOwnerId(ownerId);
        return dto;
    }

    @Then("The response status is {int}")
    public void theResponseStatusIs(int status) {
        Assertions.assertEquals(status, response.getStatusCode().value());
    }

    @And("The car ID is {int}")
    public void theCarIDIs(int id) throws JsonProcessingException {
        Car car = mapper.readValue(response.getBody(), Car.class);
        Assertions.assertEquals(id, car.getId());
    }
}