package carrental.comparators;

import carrental.Car;

import java.util.Comparator;

public class CarByPriceComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        int result = Long.compare(o1.getPricePerDayInCents(), o2.getPricePerDayInCents());
        if (result != 0) return result;

        result = Integer.compare(o2.getYear(), o1.getYear());
        if (result != 0) return result;

        result = String.CASE_INSENSITIVE_ORDER.compare(o1.getMake(), o2.getMake());
        if (result != 0) return result;

        result = String.CASE_INSENSITIVE_ORDER.compare(o1.getModel(), o2.getModel());
        if (result != 0) return result;

        return Long.compare(o1.getId(), o2.getId());
    }
}
