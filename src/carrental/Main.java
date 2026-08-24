package carrental;

import carrental.pricing.LongTermPriceCalculator;
import carrental.pricing.PriceCalculator;
import carrental.pricing.StandardPriceCalculator;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.RepositoryUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Car car1 = new Car(1, "Toyota", "Camry", 2020, 7000,
                CarType.STANDARD);
        Car car2 = new Car(2, "Volkswagen", "Golf", 2018, 5000,
                CarType.STANDARD);
        Customer customer = new Customer(1, "Robert Jackson", "02551538921");
        PriceCalculator standardPriceCalculator = new StandardPriceCalculator();
        PriceCalculator longTermPriceCalculator = new LongTermPriceCalculator();


        System.out.println("Booking 1");
        Booking booking1 = new Booking(1, car1, customer, 3, standardPriceCalculator);
        System.out.println(booking1.calculatePrice());
        System.out.println(booking1.getStatus());
        System.out.println(car1.isAvailable());

        booking1.complete();
        System.out.println(booking1.getStatus());
        System.out.println(car1.isAvailable());

        System.out.println("Booking 2");
        Booking booking2 = new Booking(2, car2, customer, 10, longTermPriceCalculator);
        System.out.println(booking2.calculatePrice());
        System.out.println(booking2.getStatus());
        System.out.println(car2.isAvailable());

        try {
            booking1.complete();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(booking2.getStatus());
        System.out.println(car2.isAvailable());
        booking2.cancel();



        // day 3
        // car repository
        System.out.println();
        System.out.println("Day 3");
        CarRepository carRepository = new CarRepository();
        System.out.println(carRepository.add(car1));
        System.out.println(carRepository.add(car2));

        System.out.println(carRepository.add(car1));

        System.out.println(carRepository.count());

        System.out.println(carRepository.findById(2L));
        try {
            System.out.println(carRepository.findById(-1L));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(carRepository.findById(100L));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        // booking repository
        Booking booking3 = new Booking(1, car1, customer, 3, standardPriceCalculator);
        BookingRepository bookingRepository = new BookingRepository();
        bookingRepository.add(booking3);

        System.out.println(carRepository.findAvailableCars());
        System.out.println(bookingRepository.findByStatus(BookingStatus.ACTIVE));

        booking3.complete();
        System.out.println(carRepository.findAvailableCars());
        System.out.println(bookingRepository.findByStatus(BookingStatus.COMPLETED));

        bookingRepository.findAll().clear();
        System.out.println(bookingRepository.findAll());

        // day 4
        System.out.println();
        System.out.println("Day 4");

        Money money1 = new Money(1000);
        Money money2 = new Money(500);

        Money moneyAdd = money1.add(money2);
        System.out.println(moneyAdd.getAmountInCents());
        System.out.println(money1.getAmountInCents());
        System.out.println(money2.getAmountInCents());

        Money moneyMultiply = money1.multiply(3);
        System.out.println(moneyMultiply.getAmountInCents());

        Money money3 = new Money(1000);
        System.out.println(money1.equals(money3));
        System.out.println(money1.hashCode() == money3.hashCode());

        Set<Money> moneySet = new HashSet<>();
        moneySet.add(money1);
        moneySet.add(money3);
        System.out.println(moneySet.size());

        try {
            Money money4 = new Money(-1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Money moneyAdd2 = money1.add(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Money moneyMultiply2 = money1.multiply(-1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        Car car3 = new Car(3, "Volkswagen", "Passat", 2025, 10000,
                CarType.PREMIUM);
        Car car4 = new Car(3, "Honda", "Accord", 2020, 75000,
                CarType.STANDARD);

        System.out.println(car3.equals(car4));
        System.out.println(car3.hashCode() == car4.hashCode());

        Set<Car> carSet = new HashSet<>();
        carSet.add(car3);
        carSet.add(car4);
        System.out.println(carSet.size());

        car3.rent();
        System.out.println(carSet.size());
        car3.returnTheCar();

        Customer customer3 = new Customer(3, "Lane Gilbert", "25678883");
        Customer customer4 = new Customer(3, "Peter Gilbert", "388326743");

        System.out.println(customer3.equals(customer4));
        System.out.println(customer3.hashCode() == customer4.hashCode());

        Set<Customer> customerSet = new HashSet<>();
        customerSet.add(customer3);
        customerSet.add(customer4);
        System.out.println(customerSet.size());

        CarSummary carSummary1 = new CarSummary(car1.getId(), car1.getMake(), car1.getModel(), car1.getYear(),
                car1.getCarType(), new Money(car1.getPricePerDayInCents()), car1.isAvailable());
        CarSummary carSummary2 = new CarSummary(car1.getId(), car1.getMake(), car1.getModel(), car1.getYear(),
                car1.getCarType(), new Money(car1.getPricePerDayInCents()), car1.isAvailable());
        System.out.println(carSummary1.equals(carSummary2));

        car1.rent();
        System.out.println(carSummary1.available());

        // day 5
        System.out.println();
        System.out.println("Day 5");
        System.out.println(carRepository.findAll());
        System.out.println(bookingRepository.findAll());

        // add
        carRepository.add(new Car(4L, "Volkswagen", "Tiguan", 2023,
                70_00L, CarType.SUV));
        bookingRepository.add(new Booking(4L, carRepository.findById(4L), customer3, 10,
                new LongTermPriceCalculator()));

        // delete
        carRepository.removeById(4L);
        try {
            System.out.println(carRepository.findById(4L));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        bookingRepository.removeById(4L);
        try {
            System.out.println(bookingRepository.findById(4L));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        // null id
        try {
            carRepository.findById(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            bookingRepository.removeById(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // invalid id
        try {
            carRepository.removeById(0L);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            bookingRepository.findById(0L);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            carRepository.removeById(-4L);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            bookingRepository.findById(-4L);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // addAll()
        System.out.println(
            RepositoryUtils.addAll(carRepository,
                List.of(
                        new Car(5L, "Toyota", "RAV4", 2023, 75_00, CarType.SUV),
                        new Car(6L, "Tesla", "Model 3", 2022, 110_00, CarType.PREMIUM),
                        new Car(7L, "Porsche", "911", 2024, 300_00, CarType.PREMIUM)
                )
            )
        );

        System.out.println(
            RepositoryUtils.addAll(bookingRepository,
                List.of(
                        new Booking(5L, carRepository.findById(6L), customer3, 3, new StandardPriceCalculator()),
                        new Booking(6L, carRepository.findById(5L), customer4, 7, new LongTermPriceCalculator()),
                        new Booking(7L, carRepository.findById(7L), customer3, 2, new StandardPriceCalculator())
                )
            )
        );

        // existed id
        System.out.println(
            RepositoryUtils.addAll(carRepository,
                List.of(new Car(5L, "Toyota", "RAV4", 2023, 75_00, CarType.SUV))
            )
        );

        bookingRepository.findById(5L).complete();
        System.out.println(
                RepositoryUtils.addAll(bookingRepository,
                        List.of(new Booking(5L, carRepository.findById(6L), customer3, 3, new StandardPriceCalculator()))
                )
        );

        // empty list
        System.out.println(
                RepositoryUtils.addAll(carRepository, new ArrayList<>())
        );

        System.out.println(
                RepositoryUtils.addAll(bookingRepository, new ArrayList<>())
        );

        // empty list with null
        List<Car> carListWithNull = new ArrayList<>();
        try {
            carListWithNull.add(new Car(8L, "Toyota", "RAV4", 2023, 75_00, CarType.SUV));
            carListWithNull.add(null);
            RepositoryUtils.addAll(carRepository, carListWithNull);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        List<Booking> bookingListWithNull = new ArrayList<>();
        try {
            bookingListWithNull.add(new Booking(5L, carRepository.findById(2L), customer3, 3, new StandardPriceCalculator()));
            bookingListWithNull.add(null);
            RepositoryUtils.addAll(bookingRepository, bookingListWithNull);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // findAllByIds
        System.out.println(
            RepositoryUtils.findAllByIds(carRepository, List.of(1L, 5L, 6L))
        );

        System.out.println(
            RepositoryUtils.findAllByIds(bookingRepository, List.of(1L, 5L, 6L))
        );

        // repeated id
        System.out.println(
                RepositoryUtils.findAllByIds(carRepository, List.of(5L, 5L, 5L))
        );

        System.out.println(
                RepositoryUtils.findAllByIds(bookingRepository, List.of(5L, 5L, 5L))
        );

        // empty list
        System.out.println(
                RepositoryUtils.findAllByIds(carRepository, List.of())
        );

        System.out.println(
                RepositoryUtils.findAllByIds(bookingRepository, List.of())
        );

        // list with null
        List<Long> ids = new ArrayList<>();
        ids.add(5L);
        ids.add(null);

        try {
            System.out.println(
                    RepositoryUtils.findAllByIds(carRepository, ids)
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(
                    RepositoryUtils.findAllByIds(bookingRepository, ids)
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // not existed id
        try {
            System.out.println(
                    RepositoryUtils.findAllByIds(carRepository, List.of(20L))
            );
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(
                    RepositoryUtils.findAllByIds(bookingRepository, List.of(20L))
            );
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}