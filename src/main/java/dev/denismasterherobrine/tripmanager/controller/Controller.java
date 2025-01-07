package dev.denismasterherobrine.tripmanager.controller;

import dev.denismasterherobrine.tripmanager.model.Booking;
import dev.denismasterherobrine.tripmanager.model.Client;
import dev.denismasterherobrine.tripmanager.model.Tour;
import dev.denismasterherobrine.tripmanager.service.BookingService;
import dev.denismasterherobrine.tripmanager.service.ClientService;
import dev.denismasterherobrine.tripmanager.service.TourService;

import java.util.List;

public class Controller {
    private final ClientService clientService;
    private final TourService tourService;
    private final BookingService bookingService;

    public Controller(ClientService clientService, TourService tourService, BookingService bookingService) {
        this.clientService = clientService;
        this.tourService = tourService;
        this.bookingService = bookingService;
    }

    public void addClient(Client client) {
        clientService.addClient(client);
    }

    public List<Client> getClients() {
        return clientService.getClients();
    }

    public void addTour(Tour tour) {
        tourService.addTour(tour);
    }

    public List<Tour> getTours() {
        return tourService.getTours();
    }

    public void addBooking(Booking booking) {
        bookingService.addBooking(booking);
    }

    public List<Booking> getBookings() {
        return bookingService.getBookings();
    }

    public List<Booking> getBookingsByClient(Client client) {
        return bookingService.getBookingsByClient(client);
    }
}
