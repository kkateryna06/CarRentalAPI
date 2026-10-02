package carrental.pricing;

import carrental.Car;

public class LongTermPriceCalculator implements PriceCalculator {
    @Override
    public long calculatePrice(Car car, int days) {
        return (car.getPricePerDayInCents() * days) * 90 / 100;
    }
}
