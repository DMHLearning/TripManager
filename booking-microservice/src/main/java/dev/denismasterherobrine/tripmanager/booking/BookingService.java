package dev.denismasterherobrine.tripmanager.booking;

import dev.denismasterherobrine.tripmanager.client.Client;
import dev.denismasterherobrine.tripmanager.tour.Tour;

import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private final List<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }

        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    public List<Booking> getBookingsByClient(Client client) {
        List<Booking> result = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getClient().equals(client)) {
                result.add(booking);
            }
        }

        return result;
    }

    public List<Client> getClientsByTour(Tour tour) {
        List<Client> clients = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getTour().equals(tour)) {
                clients.add(booking.getClient());
            }
        }

        return clients;
    }
}

