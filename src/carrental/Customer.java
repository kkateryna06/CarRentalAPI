package carrental;

public class Customer {
    private final long id;
    private String name;
    private final String licenseNumber;

    Customer(long id, String name, String licenseNumber) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        } else if (licenseNumber == null || licenseNumber.isBlank()) {
            throw new IllegalArgumentException("License number is required");
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
}
