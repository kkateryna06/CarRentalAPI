package carrental;

import carrental.comparators.CarByPriceComparator;
import carrental.repository.CarRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarAnalytics {
    private final CarRepository carRepository;

    public CarAnalytics(CarRepository carRepository) {
        if (carRepository == null) throw new IllegalArgumentException("Car repository can't be null");

        this.carRepository = carRepository;
    }

    public List<Car> findAvailableCars() {
        return carRepository.findAll().stream()
                .filter(Car::isAvailable)
                .toList();
    }

    public double calculateAveragePricePerDayInCents() {
        return carRepository.findAll().stream()
                .mapToLong(Car::getPricePerDayInCents)
                .average()
                .orElse(0);
    }

    public Map<String, List<Car>> groupCarsByMake() {
        return carRepository.findAll().stream()
                .collect(Collectors.groupingBy(Car::getMake));
    }

    public Optional<Car> findCheapestCar() {
        return carRepository.findAll().stream()
                .min(new CarByPriceComparator());
    }
}
