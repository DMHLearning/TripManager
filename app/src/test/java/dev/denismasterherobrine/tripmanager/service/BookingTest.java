package dev.denismasterherobrine.tripmanager.service;

import dev.denismasterherobrine.tripmanager.booking.Booking;
import dev.denismasterherobrine.tripmanager.client.Client;
import dev.denismasterherobrine.tripmanager.tour.Tour;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookingTest {

    @Test
    public void testBookingCreation() {
        Client client = new Client("Bob", "bob@example.com");
        Tour tour = new Tour("River Cruise", "Explore the river", 200.0, 4, 8);
        Booking booking = new Booking(client, tour);

        booking.setBookingDate("2024-12-25");
        booking.setSeatsReserved(3);

        assertEquals(client, booking.getClient(), "Client should be correctly set");
        assertEquals(tour, booking.getTour(), "Tour should be correctly set");
        assertEquals("2024-12-25", booking.getBookingDate(), "Booking date should be correctly set");
        assertEquals(3, booking.getSeatsReserved(), "Seats reserved should be correctly set");
    }

    @Test
    public void testSetInvalidBookingDate() {
        Client client = new Client("Charlie", "charlie@example.com");
        Tour tour = new Tour("Desert Trek", "Explore the desert", 250.0, 5, 10);
        Booking booking = new Booking(client, tour);

        assertThrows(IllegalArgumentException.class, () -> booking.setBookingDate(null), "Booking date cannot be null");
        assertThrows(IllegalArgumentException.class, () -> booking.setBookingDate(""), "Booking date cannot be empty");
    }

    @Test
    public void testSetInvalidSeatsReserved() {
        Client client = new Client("Dana", "dana@example.com");
        Tour tour = new Tour("Forest Hike", "Explore the forest", 200.0, 4, 10);
        Booking booking = new Booking(client, tour);

        assertThrows(IllegalArgumentException.class, () -> booking.setSeatsReserved(0), "Seats reserved must be greater than zero");
    }

    @Test
    public void testToString() {
        Client client = new Client("Eve", "eve@example.com");
        Tour tour = new Tour("Cultural Escape", "Explore the cultural heritage", 300.0, 3, 15);
        Booking booking = new Booking(client, tour);
        booking.setBookingDate("2025-01-01");
        booking.setSeatsReserved(5);

        assertEquals("Booking{client=Eve, tour='Cultural Escape', bookingDate='2025-01-01', seatsReserved=5}",
                booking.toString(), "toString should return correct representation");
    }
}
