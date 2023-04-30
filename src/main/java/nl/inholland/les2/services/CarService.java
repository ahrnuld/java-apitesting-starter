package nl.inholland.les2.services;

import nl.inholland.les2.exceptions.InhollandValidationException;
import nl.inholland.les2.models.Car;

import java.util.List;

public interface CarService {
    List<Car> getAll();

    Car getById(long id);

    Car add(Car car);

    Car update(Car car, long id) throws InhollandValidationException;

    void delete(long id);
}
