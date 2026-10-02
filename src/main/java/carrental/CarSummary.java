package carrental;

record CarSummary (
        long id,
        String make,
        String model,
        int year,
        CarType carType,
        Money pricePerDay,
        boolean available
) {
    CarSummary {
        if (id < 1) {
            throw new IllegalArgumentException("Id must be greater than 0");
        } else if (make == null || make.isBlank()) {
            throw new IllegalArgumentException("Make cannot be null");
        } else if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model cannot be null");
        } else if (year <= 0 || year > 2026) {
            throw new IllegalArgumentException("Invalid year");
        } else if (carType == null) {
            throw new IllegalArgumentException("Car type can't be null");
        } else if (pricePerDay == null) {
            throw new IllegalArgumentException("Price per day can't be null");
        }
    }
}
