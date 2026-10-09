package carrental.demo;

import carrental.Car;
import carrental.CarType;
import carrental.Customer;
import carrental.exception.CarUnavailableException;
import carrental.pricing.LongTermPriceCalculator;
import carrental.pricing.PriceCalculator;
import carrental.pricing.StandardPriceCalculator;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.RepositoryUtils;
import carrental.service.BookingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class RentalDemo implements CommandLineRunner {
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;
    private final BookingService bookingService;

    public RentalDemo(
            CarRepository carRepository,
            BookingRepository bookingRepository,
            BookingService bookingService
    ) {
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
        this.bookingService = bookingService;
    }

    @Override
    public void run(String... args) {
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

        bookingService.createBooking(1, 1, customers.get(0),
                LocalDate.of(2026, 9, 12),
                LocalDate.of(2026, 9, 14),
                standardPriceCalculator
        );
        bookingService.createBooking(2, 4, customers.get(4),
                LocalDate.of(2026, 9, 12),
                LocalDate.of(2026, 9, 14),
                standardPriceCalculator
        );
        System.out.println(bookingRepository.count());
        System.out.println(bookingRepository.findById(1L).orElseThrow().getStatus());
        System.out.println(carRepository.findById(1L).orElseThrow().isAvailable());

        // unavailable car
        try {
            bookingService.createBooking(3, 1, customers.get(1),
                    LocalDate.of(2026, 9, 14),
                    LocalDate.of(2026, 9, 20),
                    longPriceCalculator
            );
        } catch (CarUnavailableException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(bookingRepository.count());

        // existed booking id
        try {
            bookingService.createBooking(1, 2, customers.get(1),
                    LocalDate.of(2026, 9, 2),
                    LocalDate.of(2026, 9, 10),
                    longPriceCalculator
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(bookingRepository.count());

        // invalid date
        try {
            bookingService.createBooking(3, 2, customers.get(1),
                    LocalDate.of(2026, 9, 10),
                    LocalDate.of(2026, 9, 2),
                    longPriceCalculator
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(bookingRepository.count());
        System.out.println(carRepository.findById(2L).orElseThrow().isAvailable());

        bookingService.cancelBooking(1);
        bookingService.completeBooking(2);
        System.out.println(bookingRepository.findById(1L).orElseThrow().getStatus());
        System.out.println(bookingRepository.findById(1L).orElseThrow().getCar().isAvailable());
        System.out.println(bookingRepository.findById(2L).orElseThrow().getStatus());
        System.out.println(bookingRepository.findById(2L).orElseThrow().getCar().isAvailable());

        try {
            bookingService.cancelBooking(2);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        bookingService.createBooking(3, 1, customers.get(2),
                LocalDate.of(2026, 10, 9),
                LocalDate.of(2026, 10, 19),
                longPriceCalculator);

        try {
            bookingService.cancelBooking(1);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(bookingRepository.findById(3L).orElseThrow().getStatus());
        System.out.println(bookingRepository.findById(3L).orElseThrow().getCar().isAvailable());
    }

}
