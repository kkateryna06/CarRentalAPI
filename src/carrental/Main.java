package carrental;

import carrental.pricing.LongTermPriceCalculator;
import carrental.pricing.PriceCalculator;
import carrental.pricing.StandardPriceCalculator;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        Car car1 = new Car(1, "Toyota", "Camry", 2020, 7000);
        Car car2 = new Car(2, "Volkswagen", "Golf", 2018, 5000);
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
    }
}