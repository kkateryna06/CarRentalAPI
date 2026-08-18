package carrental;

import carrental.pricing.LongTermPriceCalculator;
import carrental.pricing.PriceCalculator;
import carrental.pricing.StandardPriceCalculator;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

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

        System.out.println(carRepository.findById(2));
        try {
            System.out.println(carRepository.findById(-1));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(carRepository.findById(100));
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
    }
}