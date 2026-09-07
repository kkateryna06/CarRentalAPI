package carrental;

import carrental.comparators.CarByMakeComparator;
import carrental.comparators.CarByPriceComparator;
import carrental.comparators.CarByYearComparator;
import carrental.pricing.LongTermPriceCalculator;
import carrental.pricing.PriceCalculator;
import carrental.pricing.StandardPriceCalculator;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.RepositoryUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        CarRepository carRepository = new CarRepository();
        BookingRepository bookingRepository = new BookingRepository();
        List<Car> cars = List.of(
                new Car(1L, "Toyota", "Yaris", 2020, 12_000L, CarType.ECONOMY),
                new Car(2L, "Volkswagen", "Polo", 2021, 12_000L, CarType.ECONOMY),
                new Car(3L, "Skoda", "Octavia", 2022, 18_000L, CarType.STANDARD),
                new Car(4L, "Mazda", "3", 2021, 19_500L, CarType.STANDARD),
                new Car(5L, "Toyota", "RAV4", 2023, 27_000L, CarType.SUV),
                new Car(6L, "Kia", "Sportage", 2022, 25_000L, CarType.SUV),
                new Car(7L, "BMW", "5 Series", 2023, 42_000L, CarType.PREMIUM),
                new Car(8L, "Audi", "A6", 2022, 40_000L, CarType.PREMIUM),
                new Car(9L, "Hyundai", "i30", 2019, 15_000L, CarType.STANDARD),
                new Car(10L, "Dacia", "Duster", 2020, 20_000L, CarType.SUV),
                new Car(11L, "Mazda", "6", 2021, 22_500L, CarType.STANDARD)

        );
        RepositoryUtils.addAll(carRepository, cars);

        List<Customer> customers = List.of(
                new Customer(1L, "James Smith", "12345678"),
                new Customer(2L, "Emily Johnson", "87654321"),
                new Customer(3L, "Michael Brown", "45678123"),
                new Customer(4L, "Olivia Davis", "98761234"),
                new Customer(5L, "Daniel Wilson", "23456789"),
                new Customer(6L, "Sophia Taylor", "76543210")
        );

        PriceCalculator standardPriceCalculator = new StandardPriceCalculator();
        PriceCalculator longPriceCalculator = new LongTermPriceCalculator();
        List<Booking> bookings = List.of(
                new Booking(
                        1L,
                        cars.get(0),
                        customers.get(0),
                        3,
                        standardPriceCalculator
                ),
                new Booking(
                        2L,
                        cars.get(1),
                        customers.get(1),
                        7,
                        longPriceCalculator
                ),
                new Booking(
                        3L,
                        cars.get(2),
                        customers.get(2),
                        2,
                        standardPriceCalculator
                ),
                new Booking(
                        4L,
                        cars.get(3),
                        customers.get(0),
                        5,
                        standardPriceCalculator
                ),
                new Booking(
                        5L,
                        cars.get(4),
                        customers.get(1),
                        10,
                        longPriceCalculator
                ),
                new Booking(
                        6L,
                        cars.get(5),
                        customers.get(2),
                        4,
                        standardPriceCalculator
                ),
                new Booking(
                        7L,
                        cars.get(7),
                        customers.get(0),
                        1,
                        standardPriceCalculator
                ),
                new Booking(
                        8L,
                        cars.get(9),
                        customers.get(1),
                        14,
                        longPriceCalculator
                )
        );
        RepositoryUtils.addAll(bookingRepository, bookings);

        CarAnalytics carAnalytics = new CarAnalytics(carRepository);
        System.out.println(carAnalytics.findAvailableCars());
        System.out.println(carAnalytics.calculateAveragePricePerDayInCents());
        System.out.println(carAnalytics.groupCarsByMake());
        System.out.println(carAnalytics.findCheapestCar());

        CarAnalytics  emptyCarAnalytics = new CarAnalytics(new CarRepository());
        System.out.println(emptyCarAnalytics.findAvailableCars());
        System.out.println(emptyCarAnalytics.calculateAveragePricePerDayInCents());
        System.out.println(emptyCarAnalytics.groupCarsByMake());
        System.out.println(emptyCarAnalytics.findCheapestCar());

        try {
            new CarAnalytics(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(carRepository.findById(4L)
                .map(car -> car.getMake() + " " + car.getModel())
                .orElse("No car was found"));
        System.out.println(carRepository.findById(15L)
                .map(car -> car.getMake() + " " + car.getModel())
                .orElse("No car was found"));
    }
}