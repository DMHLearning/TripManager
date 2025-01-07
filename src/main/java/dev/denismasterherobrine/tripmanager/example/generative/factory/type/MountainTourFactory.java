package dev.denismasterherobrine.tripmanager.example.generative.factory.type;

import dev.denismasterherobrine.tripmanager.example.generative.factory.TourFactory;
import dev.denismasterherobrine.tripmanager.model.Tour;

public class MountainTourFactory extends TourFactory {
    @Override
    public Tour createTour(String name, String description, double price, int duration, int availableSeats) {
        return new Tour("Mountain Tour", description, price, duration, availableSeats);
    }
}