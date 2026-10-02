package carrental.service;

import carrental.Booking;
import carrental.Car;
import carrental.Customer;
import carrental.exception.BookingNotFoundException;
import carrental.exception.CarNotFoundException;
import carrental.exception.CarUnavailableException;
import carrental.pricing.PriceCalculator;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;

import java.time.LocalDate;

public class BookingService {
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;

    public BookingService(
            CarRepository carRepository,
            BookingRepository bookingRepository
    ) {
        if (bookingRepository == null)
            throw new IllegalArgumentException("Booking repository can't be null");
        if (carRepository == null)
            throw new IllegalArgumentException("Car repository can't be null");

        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(
            long bookingId,
            long carId,
            Customer customer,
            LocalDate startDate,
            LocalDate endDate,
            PriceCalculator priceCalculator
    ) {
        if (bookingId < 1)
            throw new IllegalArgumentException("Booking id must be greater than 0");
        if (carId < 1)
            throw new IllegalArgumentException("Car id must be greater than 0");
        if (bookingRepository.findById(bookingId).isPresent())
            throw new IllegalArgumentException("Booking with id " + bookingId + " already exists");

        Car car = carRepository.findById(carId).orElseThrow( () ->
                new CarNotFoundException("Car with id " + carId + " was not found")
        );
        if (!car.isAvailable())
            throw new CarUnavailableException("Car with id " + carId + " is unavailable");

        Booking booking = new Booking(bookingId, car, customer, startDate, endDate, priceCalculator);
        boolean result = bookingRepository.add(booking);

        if (!result)
            throw new IllegalArgumentException("Car with id " + carId + " was not added to repository");

        car.rent();
        return booking;
    }


    public void cancelBooking(long bookingId) {
        if (bookingId < 1)
            throw new IllegalArgumentException("Booking id must be greater than 0");

        Booking booking = bookingRepository.findById(bookingId).orElseThrow( () ->
                new BookingNotFoundException("Booking with id " + bookingId + " was not found")
        );

        booking.cancel();
        booking.getCar().returnTheCar();
    }

    public void completeBooking(long bookingId) {
        if (bookingId < 1)
            throw new IllegalArgumentException("Booking id must be greater than 0");

        Booking booking = bookingRepository.findById(bookingId).orElseThrow( () ->
                new BookingNotFoundException("Booking with id " + bookingId + " was not found")
        );

        booking.complete();
        booking.getCar().returnTheCar();
    }
}
