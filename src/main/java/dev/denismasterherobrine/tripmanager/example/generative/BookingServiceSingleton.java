package dev.denismasterherobrine.tripmanager.example.generative;

import dev.denismasterherobrine.tripmanager.model.Booking;

import java.util.ArrayList;
import java.util.List;

// Порождающий паттерн: Singleton для BookingService
public class BookingServiceSingleton {
    private static BookingServiceSingleton instance;
    private final List<Booking> bookings = new ArrayList<>();

    private BookingServiceSingleton() {}

    public static BookingServiceSingleton getInstance() {
        if (instance == null) {
            instance = new BookingServiceSingleton();
        }

        return instance;
    }

    public void addBooking(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }

        bookings.add(booking);

        System.out.println("Booking added: " + booking);
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
}
