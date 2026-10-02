package carrental;

import carrental.pricing.PriceCalculator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {
    private final long id;
    private final Car car;
    private final Customer customer;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private BookingStatus status;
    private final PriceCalculator priceCalculator;

    public Booking(long id, Car car, Customer customer, LocalDate startDate, LocalDate endDate,
            PriceCalculator priceCalculator) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        } else if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        } else if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }  else if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null");
        } else if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        } else if (ChronoUnit.DAYS.between(startDate, endDate) > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Booking period is too long");
        } else if (priceCalculator == null) {
            throw new IllegalArgumentException("Price Calculator must be specified");
        } else if (id < 1) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        this.id = id;
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = BookingStatus.ACTIVE;
        this.priceCalculator = priceCalculator;
    }

    public long getId() {
        return id;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getDaysForRent() {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    public long calculatePrice() {
        return priceCalculator.calculatePrice(car, getDaysForRent());
    }

    public Car getCar() {
        return car;
    }

    public void cancel() {
        if (status == BookingStatus.ACTIVE) {
            status = BookingStatus.CANCELED;
        } else {
            throw new IllegalStateException("Booking status must be ACTIVE");
        }
    }

    public void complete() {
        if (status == BookingStatus.ACTIVE) {
            status = BookingStatus.COMPLETED;
        } else {
            throw new IllegalStateException("Booking status must be ACTIVE");
        }
    }
}
