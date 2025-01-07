package dev.denismasterherobrine.tripmanager.example.structural;

import dev.denismasterherobrine.tripmanager.model.Tour;

// Cтруктурный паттерн: Decorator (Tour, но с возможностью навесить скидку)
public class TourWithDiscountDecorator extends Tour {
    private final Tour decoratedTour;
    private final double discount;

    public TourWithDiscountDecorator(Tour decoratedTour, double discount) {
        super(decoratedTour.getName(), decoratedTour.getPrice());
        this.decoratedTour = decoratedTour;
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return decoratedTour.getPrice() * (1 - discount);
    }

    @Override
    public String toString() {
        return decoratedTour.toString() + " with discount: " + (discount * 100) + "%";
    }
}
