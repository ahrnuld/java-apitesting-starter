package nl.inholland.les2.controllers;

import nl.inholland.les2.exceptions.InhollandValidationException;
import nl.inholland.les2.models.Car;
import nl.inholland.les2.models.DTO.CarDTO;
import nl.inholland.les2.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {

    @Autowired
    private CarService carService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public List<CarDTO> getAll() {

        List<CarDTO> carDTOS = new ArrayList<>();
        modelMapper.map(carService.getAll(),carDTOS);

        return carDTOS;
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable long id) {
        return carService.getById(id);
    }

    @PostMapping
    public Car add(@RequestBody Car car) {
        return carService.add(car);
    }

    @PutMapping("/{id}")
    public Car update(@RequestBody Car car, @PathVariable long id) throws InhollandValidationException {

            return carService.update(car, id);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        carService.delete(id);
    }

}
