package dev.denismasterherobrine.tripmanager.service;

import dev.denismasterherobrine.tripmanager.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourService {
    private final List<Tour> tours = new ArrayList<>();

    public void addTour(Tour tour) {
        if (tour == null) {
            throw new IllegalArgumentException("Tour cannot be null");
        }
        tours.add(tour);
    }

    public List<Tour> getTours() {
        return new ArrayList<>(tours);
    }
}
