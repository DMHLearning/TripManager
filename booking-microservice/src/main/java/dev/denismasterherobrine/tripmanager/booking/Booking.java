package dev.denismasterherobrine.tripmanager.booking;

import dev.denismasterherobrine.tripmanager.client.Client;
import dev.denismasterherobrine.tripmanager.tour.Tour;

public class Booking {
    private Client client;
    private Tour tour;
    private String bookingDate;
    private int seatsReserved;

    public Booking(Client client, Tour tour) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }

        if (tour == null) {
            throw new IllegalArgumentException("Tour cannot be null");
        }

        this.client = client;
        this.tour = tour;
    }

    public Client getClient() {
        return client;
    }

    public Tour getTour() {
        return tour;
    }

    public void setBookingDate(String bookingDate) {
        if (bookingDate == null || bookingDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Booking date cannot be null or empty");
        }
        this.bookingDate = bookingDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setSeatsReserved(int seatsReserved) {
        if (seatsReserved <= 0) {
            throw new IllegalArgumentException("Seats reserved must be greater than zero");
        }
        this.seatsReserved = seatsReserved;
    }

    public int getSeatsReserved() {
        return seatsReserved;
    }

    @Override
    public String toString() {
        return "Booking{client=" + client.getName() + ", tour='" + tour.getName() + "', bookingDate='" + bookingDate + "', seatsReserved=" + seatsReserved + "}";
    }
}
