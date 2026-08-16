package carrental;

import carrental.pricing.PriceCalculator;

public class Booking {
    private final long id;
    private final Car car;
    private final Customer customer;
    private final int daysForRent;
    private BookingStatus status;
    private final PriceCalculator priceCalculator;

    Booking(long id, Car car, Customer customer, int daysForRent, PriceCalculator priceCalculator) {
        if (car == null) {
            throw new IllegalArgumentException("carrental.Car cannot be null");
        } else if (customer == null) {
            throw new IllegalArgumentException("carrental.Customer cannot be null");
        } else if (daysForRent < 1) {
            throw new IllegalArgumentException("Day amount must be greater than 0");
        } else if (priceCalculator == null) {
            throw new IllegalArgumentException("Price Calculator must be specified");
        } else if (id < 1) {
            throw new IllegalArgumentException("Id must be greater than 1");
        }
        this.id = id;
        this.car = car;
        this.customer = customer;
        this.daysForRent = daysForRent;
        this.status = BookingStatus.ACTIVE;
        this.priceCalculator = priceCalculator;
        car.rent();
    }

    public long getId() {
        return id;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public long calculatePrice() {
        return priceCalculator.calculatePrice(car, daysForRent);
    }

    public void cancel() {
        if (status == BookingStatus.ACTIVE) {
            status = BookingStatus.CANCELED;
            car.returnTheCar();
        } else {
            throw new IllegalArgumentException("Booking status must be ACTIVE");
        }
    }

    public void complete() {
        if (status == BookingStatus.ACTIVE) {
            status = BookingStatus.COMPLETED;
            car.returnTheCar();
        } else {
            throw new IllegalStateException("Booking status must be ACTIVE");
        }
    }
}
