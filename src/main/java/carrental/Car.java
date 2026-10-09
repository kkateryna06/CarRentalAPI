package carrental;

import java.util.Objects;

public class Car {
    private final long id;
    private String make;
    private String model;
    private int year;
    private long pricePerDayInCents;
    private boolean isAvailable;
    private final CarType carType;

    public Car(long id, String make, String model, int year, long dayRent, CarType carType) {
        if (year <= 0 || year > 2026) {
            throw new IllegalArgumentException("Invalid year");
        } else if (dayRent <= 0) {
            throw new IllegalArgumentException("Invalid day pay rent");
        } else if (make == null || make.isBlank()) {
            throw new IllegalArgumentException("Make cannot be null");
        } else if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model cannot be null");
        } else if (id < 1) {
            throw new IllegalArgumentException("Id must be greater than 0");
        } else if (carType == null) {
            throw new IllegalArgumentException("Car type can't be null");
        }
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.pricePerDayInCents = dayRent;
        this.isAvailable = true;
        this.carType = carType;
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

    public CarType getCarType() {
        return carType;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
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

    public void printInfo() {
        System.out.println(id + " " + make + " " + model + " " + year + " " + pricePerDayInCents);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object == this) return true;

        if (!(object instanceof Car car)) return false;

        return car.getId() == id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
