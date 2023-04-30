package nl.inholland.les2.repositories;

import nl.inholland.les2.models.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Iterable<Car> findAllByBrandLike(String brand);
}
