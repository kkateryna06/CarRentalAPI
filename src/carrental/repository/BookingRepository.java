package carrental.repository;

import carrental.Booking;
import carrental.BookingStatus;

import java.util.*;


public class BookingRepository implements Repository<Booking>{
    private final Map<Long, Booking> bookings;

    public BookingRepository() {
        this.bookings = new HashMap<>();
    }

    @Override
    public boolean add(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking can't be null");
        }
        return bookings.putIfAbsent(booking.getId(), booking) == null;
    }

    @Override
    public Booking findById(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        Booking booking = bookings.get(id);
        if (booking == null) {
            throw new NoSuchElementException("No such element");
        }
        return booking;
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookings.values());
    }

    @Override
    public boolean removeById(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        return bookings.remove(id) != null;
    }

    @Override
    public int count() {
        return bookings.size();
    }

    public List<Booking> findByStatus(BookingStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status can't be null");
        }
        List<Booking> bookingsByStatus = new ArrayList<>();
        for (Booking booking : bookings.values()) {
            if (booking.getStatus() == status) {
                bookingsByStatus.add(booking);
            }
        }
        return bookingsByStatus;
    }
}
