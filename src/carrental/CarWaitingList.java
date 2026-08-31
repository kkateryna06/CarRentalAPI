package carrental;

import java.util.*;

public class CarWaitingList {
    private final Map<Long, Queue<Customer>> queuesByCarId = new HashMap<>();

    public void join(Car car, Customer customer) {
        if (car == null) throw new IllegalArgumentException("Car can't be null");
        if (customer == null) throw new IllegalArgumentException("Customer can't be null");
        if (car.isAvailable()) throw new IllegalStateException("Car must be not available");

        Queue<Customer> queue = queuesByCarId.getOrDefault(car.getId(), new ArrayDeque<>());
        if (queue.contains(customer)) throw new IllegalStateException("Customer is already in the queue");

        queue.add(customer);
        queuesByCarId.put(car.getId(), queue);
    }

    public Customer peekNext(Long carId) {
        if (carId == null) throw new IllegalArgumentException("Car id can't be null");

        Queue<Customer> queue = queuesByCarId.get(carId);
        if (queue == null) return null;

        return queue.peek();
    }

    public Customer pollNext(Long carId) {
        if (carId == null) throw new IllegalArgumentException("Car id can't be null");

        Queue<Customer> queue = queuesByCarId.get(carId);
        if (queue == null) return null;

        Customer customer = queue.poll();
        if (queue.isEmpty()) queuesByCarId.remove(carId);

        return customer;
    }

    public int waitingCount(Long carId) {
        if (carId == null) throw new IllegalArgumentException("Car id can't be null");

        Queue<Customer> queue = queuesByCarId.get(carId);
        if (queue == null) return 0;

        return queue.size();
    }

    public boolean hasWaitingCustomers(Long carId) {
        if (carId == null) throw new IllegalArgumentException("Car id can't be null");

        return !(queuesByCarId.get(carId) == null);
    }

    public List<Customer> getWaitingCustomers(Long carId) {
        if (carId == null) throw new IllegalArgumentException("Car id can't be null");

        Queue<Customer> queue = queuesByCarId.get(carId);
        if (queue == null) return new ArrayList<>();

        return new ArrayList<>(queue);
    }
}
