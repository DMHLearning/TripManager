package dev.denismasterherobrine.tripmanager.example.behavioral.command;

import dev.denismasterherobrine.tripmanager.model.Booking;
import dev.denismasterherobrine.tripmanager.service.BookingService;

public class AddBookingCommand implements ICommand {
    private final BookingService bookingService;
    private final Booking booking;

    public AddBookingCommand(BookingService bookingService, Booking booking) {
        this.bookingService = bookingService;
        this.booking = booking;
    }

    @Override
    public void execute() {
        bookingService.addBooking(booking);
        System.out.println("Booking executed for: " + booking);
    }
}

