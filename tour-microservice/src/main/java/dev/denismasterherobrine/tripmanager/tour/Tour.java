package dev.denismasterherobrine.tripmanager.tour;

public class Tour {
    private String name;
    private String description;
    private double price;
    private int duration;
    private int seats;

    public Tour(String name, String description, double price, int duration, int seats) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tour name cannot be null or empty");
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Tour description cannot be null or empty");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Tour price must be greater than 0");
        }

        if (this.seats < 0) {
            throw new IllegalArgumentException("Available seats cannot be negative");
        }

        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.seats = this.seats;
    }

    public Tour(String name, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tour name cannot be null or empty");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Tour price must be greater than 0");
        }

        this.name = name;
        this.price = price;
        this.duration = 1;
        this.seats = 1;
        this.description = "No description provided";
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than zero");
        }

        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setAvailableSeats(int availableSeats) {
        if (availableSeats < 0) {
            throw new IllegalArgumentException("Available seats cannot be negative");
        }

        this.seats = availableSeats;
    }

    public int getAvailableSeats() {
        return seats;
    }

    public void setSeats(int availableSeats) {
        this.seats = availableSeats;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return "Tour{name='" + name + "', description='" + description + "', price=" + price + ", duration=" + duration + ", seats=" + seats + "}";
    }
}