package carrental;

import java.util.Objects;

public class Customer {
    private final long id;
    private String name;
    private final String licenseNumber;

    public Customer(long id, String name, String licenseNumber) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        } else if (licenseNumber == null || licenseNumber.isBlank()) {
            throw new IllegalArgumentException("License number is required");
        } else if (id < 1) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object == this) return true;

        if (!(object instanceof Customer customer)) return false;

        return customer.getId() == id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
