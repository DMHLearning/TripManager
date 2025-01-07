package dev.denismasterherobrine.tripmanager.service;

import dev.denismasterherobrine.tripmanager.model.Booking;
import dev.denismasterherobrine.tripmanager.model.Client;
import dev.denismasterherobrine.tripmanager.model.Tour;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingServiceTest {

    @Test
    public void testAddAndGetBookings() {
        BookingService bookingService = new BookingService();
        Client client = new Client("Hank", "hank@example.com");
        Tour tour = new Tour("Historic Tour", "Explore the history", 120.0, 3, 15);
        Booking booking = new Booking(client, tour);

        bookingService.addBooking(booking);
        List<Booking> bookings = bookingService.getBookings();

        assertEquals(1, bookings.size(), "Booking list size should be 1");
        assertTrue(bookings.contains(booking), "Booking list should contain the added booking");
    }

    @Test
    public void testGetBookingsByClient() {
        BookingService bookingService = new BookingService();
        Client client = new Client("Ivy", "ivy@example.com");
        Tour tour = new Tour("Canyon Walk", "Explore the canyon", 120.0, 3, 10);
        Booking booking = new Booking(client, tour);

        bookingService.addBooking(booking);
        List<Booking> clientBookings = bookingService.getBookingsByClient(client);

        assertEquals(1, clientBookings.size(), "Client should have 1 booking");
        assertTrue(clientBookings.contains(booking), "Client's bookings should include the added booking");
    }

    @Test
    public void testGetClientsByTour() {
        BookingService bookingService = new BookingService();
        Client client = new Client("Jack", "jack@example.com");
        Tour tour = new Tour("Art Tour", "Explore the art", 250.0, 4, 8);
        Booking booking = new Booking(client, tour);

        bookingService.addBooking(booking);
        List<Client> clients = bookingService.getClientsByTour(tour);

        assertEquals(1, clients.size(), "Tour should have 1 client");
        assertTrue(clients.contains(client), "Tour's clients should include the added client");
    }
}
