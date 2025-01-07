package dev.denismasterherobrine.tripmanager.example.behavioral.observer;

import dev.denismasterherobrine.tripmanager.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourNotifier {
    private final List<IObserver> observers = new ArrayList<>();

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }

    public void newTourAvailable(Tour tour) {
        notifyObservers("New tour available: " + tour.getName());
    }
}
