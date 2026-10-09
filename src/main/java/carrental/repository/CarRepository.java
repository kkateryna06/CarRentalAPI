package carrental.repository;

import carrental.Car;

import java.util.*;

@org.springframework.stereotype.Repository
public class CarRepository implements Repository<Car, Long> {
    private final Map<Long, Car> cars;

    public CarRepository() {
        this.cars = new HashMap<>();
    }

    @Override
    public boolean add(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car can't be null");
        }
        return cars.putIfAbsent(car.getId(), car) == null;
    }

    @Override
    public Optional<Car> findById(Long id) {
        if (id == null) throw new IllegalArgumentException("Id can't be null");
        if (id < 1) throw new IllegalArgumentException("Id must be greater than 0");

        return Optional.ofNullable(cars.get(id));
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(cars.values());
    }

    @Override
    public boolean removeById(Long id) {
        if (id == null) throw new IllegalArgumentException("Id can't be null");
        if (id < 1) throw new IllegalArgumentException("Id must be greater than 0");

        return cars.remove(id) != null;
    }

    @Override
    public int count() {
        return cars.size();
    }

    public List<Car> findAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars.values()) {
            if (car.isAvailable()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }
}
