package nl.inholland.apidemo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarTest {

    Car car;

    @BeforeEach
    void initClass() {
        car = new Car();
    }

    @Test
    void newCarShouldNotBeNull() {
        assertNotNull(car);
    }

    @Test
    void carWeightShouldNotBeLessThanZero() {
        // Technically two tests
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> car.setWeight(-1));
        assertEquals("Weight cannot be less than zero", exception.getMessage());

    }

}