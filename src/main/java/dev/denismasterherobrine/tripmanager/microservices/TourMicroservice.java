package dev.denismasterherobrine.tripmanager.microservices;

import dev.denismasterherobrine.tripmanager.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourMicroservice {
    private final List<Tour> tours = new ArrayList<>();

    public void addTour(String name, String description, double price, int duration, int availableSeats) {
        Tour tour = new Tour(name, description, price, duration, availableSeats);
        tour.setAvailableSeats(availableSeats);
        tours.add(tour);
        System.out.println("Tour added: " + tour);
    }

    public List<Tour> getTours() {
        return new ArrayList<>(tours);
    }

    public boolean removeTour(String name) {
        tours.removeIf(tour -> tour.getName().equals(name));
        System.out.println("Tour removed: " + name);
        return false;
    }

    public boolean removeTourWithRelatedBookings(String name, BookingMicroservice bookingMicroservice) {
        tours.removeIf(tour -> {
            if (tour.getName().equals(name)) {
                bookingMicroservice.getBookings().removeIf(booking -> booking.getTour().equals(tour));
                return true;
            }
            return false;
        });
        System.out.println("Tour removed with related bookings: " + name);
        return false;
    }

    public Tour findTourByName(String name) {
        return tours.stream().filter(tour -> tour.getName().equals(name)).findFirst().orElse(null);
    }
}
