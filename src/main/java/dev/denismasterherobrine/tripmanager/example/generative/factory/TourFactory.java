package dev.denismasterherobrine.tripmanager.example.generative.factory;

import dev.denismasterherobrine.tripmanager.model.Tour;

// Порождающий паттерн: Factory Method (TourFactory)
public abstract class TourFactory {
    public abstract Tour createTour(String name, String description, double price, int duration, int availableSeats);
}

