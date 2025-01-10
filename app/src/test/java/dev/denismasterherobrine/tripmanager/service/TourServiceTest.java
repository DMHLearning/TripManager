package dev.denismasterherobrine.tripmanager.service;

import dev.denismasterherobrine.tripmanager.tour.Tour;
import dev.denismasterherobrine.tripmanager.tour.TourService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TourServiceTest {

    @Test
    public void testAddAndGetTours() {
        TourService tourService = new TourService();
        Tour tour1 = new Tour("Island Retreat", "Relax on the island", 300.0, 5, 10);
        Tour tour2 = new Tour("City Adventure", "Explore the city", 150.0, 3, 15);

        tourService.addTour(tour1);
        tourService.addTour(tour2);

        List<Tour> tours = tourService.getTours();
        assertEquals(2, tours.size(), "Tour list size should be 2");
        assertTrue(tours.contains(tour1), "Tour list should contain tour1");
        assertTrue(tours.contains(tour2), "Tour list should contain tour2");
    }

    @Test
    public void testAddNullTour() {
        TourService tourService = new TourService();
        assertThrows(IllegalArgumentException.class, () -> tourService.addTour(null), "Adding null tour should throw exception");
    }
}
