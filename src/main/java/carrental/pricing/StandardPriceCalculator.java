package carrental.pricing;

import carrental.Car;

public class StandardPriceCalculator implements PriceCalculator {
    @Override
    public long calculatePrice(Car car, int days) {
        return car.getPricePerDayInCents() * days;
    }
}
