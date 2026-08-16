package carrental;

public class Car {
    private final long id;
    private String make;
    private String model;
    private int year;
    private long pricePerDayInCents;
    private boolean isAvailable;

    Car(long id, String make, String model, int year, long dayRent) {
        if (year <= 0 || year > 2026) {
            throw new IllegalArgumentException("Invalid year");
        } else if (dayRent <= 0) {
            throw new IllegalArgumentException("Invalid day pay rent");
        } else if (make == null || make.isBlank()) {
            throw new IllegalArgumentException("Make cannot be null");
        } else if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model cannot be null");
        } else if (id < 1) {
            throw new IllegalArgumentException("Id must be greater than 1");
        }
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.pricePerDayInCents = dayRent;
        this.isAvailable = true;
    }

    public long getId() {
        return id;
    }

    public long getPricePerDayInCents() {
        return pricePerDayInCents;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        if (!isAvailable) {
            throw new IllegalStateException("The car is unavailable for rent");
        }
        this.isAvailable = false;
    }

    public void returnTheCar() {
        this.isAvailable = true;
    }
}
