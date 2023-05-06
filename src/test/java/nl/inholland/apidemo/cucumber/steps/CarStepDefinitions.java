package nl.inholland.apidemo.cucumber.steps;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    private ResponseEntity<String> response;

    @Given("The endpoint for {string} is available for method {string}")
    public void theEndpointForIsAvailable(String endpoint, String method) {
        response = restTemplate.exchange("/" + endpoint, HttpMethod.OPTIONS, new HttpEntity<>(null, new HttpHeaders()), String.class);
        List<String> options = Arrays.stream(response.getHeaders()
                        .get("Allow")
                        .get(0)
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
}