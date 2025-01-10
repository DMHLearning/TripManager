package dev.denismasterherobrine.tripmanager.service;

import dev.denismasterherobrine.tripmanager.tour.Tour;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TourTest {

    @Test
    public void testTourCreation() {
        Tour tour = new Tour("Beach Tour", "Explore the beach", 199.99, 4, 9);
        tour.setDuration(5);
        tour.setAvailableSeats(10);

        assertEquals("Beach Tour", tour.getName(), "Tour name should be correctly set");
        assertEquals(199.99, tour.getPrice(), "Tour price should be correctly set");
        assertEquals("Explore the beach", tour.getDescription(), "Tour description should be set correctly");
        assertEquals(5, tour.getDuration(), "Tour duration should be set correctly");
        assertEquals(10, tour.getAvailableSeats(), "Tour available seats should be set correctly");
    }

    @Test
    public void testSetInvalidDuration() {
        Tour tour = new Tour("City Tour", "Explore the city", 300.0, 3, 15);
        assertThrows(IllegalArgumentException.class, () -> tour.setDuration(0), "Duration must be greater than zero");
    }

    @Test
    public void testSetInvalidSeats() {
        Tour tour = new Tour("Mountain Tour", "Explore the mountains", 250.0, 5, 10);
        assertThrows(IllegalArgumentException.class, () -> tour.setAvailableSeats(-1), "Available seats cannot be negative");
    }

    @Test
    public void testToString() {
        Tour tour = new Tour("Safari", "Explore the wildlife", 500.0, 5, 10);
        tour.setDuration(7);
        tour.setAvailableSeats(20);

        assertEquals("Tour{name='Safari', description='Explore the wildlife', price=500.0, duration=7, availableSeats=20}",
                tour.toString(), "toString should return correct representation");
    }
}
