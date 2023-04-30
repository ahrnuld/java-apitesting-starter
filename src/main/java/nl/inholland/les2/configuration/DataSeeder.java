package nl.inholland.les2.configuration;

import nl.inholland.les2.models.Car;
import nl.inholland.les2.models.Person;
import nl.inholland.les2.repositories.PersonRepository;
import nl.inholland.les2.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataSeeder implements ApplicationRunner {

    @Autowired
    CarService carService;

    @Autowired
    PersonRepository personRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Person person1 = new Person("Luc", "Besson", new ArrayList<>());


        Car car1 = new Car("Mercedes", "A class", person1);
        person1.getCars().add(car1);
        personRepository.save(person1);
        carService.add(car1);
    }
}
