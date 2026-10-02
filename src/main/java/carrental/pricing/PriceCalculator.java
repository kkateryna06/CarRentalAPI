package carrental.pricing;

import carrental.Car;

public interface PriceCalculator {
    long calculatePrice(Car car, int days);
}
