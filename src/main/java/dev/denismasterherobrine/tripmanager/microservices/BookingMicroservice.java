package dev.denismasterherobrine.tripmanager.microservices;

import dev.denismasterherobrine.tripmanager.model.Booking;
import dev.denismasterherobrine.tripmanager.model.Client;
import dev.denismasterherobrine.tripmanager.model.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingMicroservice {
    private final List<Booking> bookings = new ArrayList<>();

    // Добавление бронирования
    public void addBooking(Client client, Tour tour, String bookingDate, int seats) {
        if (tour.getAvailableSeats() >= seats) {
            Booking booking = new Booking(client, tour);
            booking.setBookingDate(bookingDate);
            booking.setSeatsReserved(seats);
            bookings.add(booking);

            // Обновляем доступные места в туре
            tour.setAvailableSeats(tour.getAvailableSeats() - seats);
            System.out.println("Booking added: " + booking);
        } else {
            System.out.println("Not enough seats available for tour: " + tour.getName());
        }
    }

    // Получение всех бронирований
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    // Поиск бронирований по клиенту
    public List<Booking> findBookingsByClient(Client client) {
        return bookings.stream().filter(booking -> booking.getClient().equals(client)).collect(Collectors.toList());
    }

    // Удаление бронирования
    public boolean cancelBooking(Client client, Tour tour) {
        Optional<Booking> bookingToRemove = bookings.stream().filter(b -> b.getClient().equals(client) && b.getTour().equals(tour)).findFirst();
        if (bookingToRemove.isPresent()) {
            bookings.remove(bookingToRemove.get());
            // Возвращаем места в тур
            tour.setAvailableSeats(tour.getAvailableSeats() + bookingToRemove.get().getSeatsReserved());
            System.out.println("Booking canceled: " + bookingToRemove.get());
            return true;
        }
        System.out.println("Booking not found for client: " + client.getName());
        return false;
    }
}
