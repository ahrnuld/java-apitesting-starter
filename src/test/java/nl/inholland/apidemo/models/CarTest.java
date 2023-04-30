package nl.inholland.apidemo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void newCarShouldNotBeNull() {
        Car car = new Car();
        assertNotNull(car);
    }

}