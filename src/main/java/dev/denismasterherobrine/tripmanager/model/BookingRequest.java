package dev.denismasterherobrine.tripmanager.model;

public class BookingRequest {
    private String email;
    private String tourName;
    private String bookingDate;
    private int seatsReserved;

    public BookingRequest(String email, String tourName, String bookingDate, int seats) {
        this.email = email;
        this.tourName = tourName;
        this.bookingDate = bookingDate;
        this.seatsReserved = seats;
    }

    // Геттеры и сеттеры
    public String getEmail() {
        return email;
    }

    public String getTourName() {
        return tourName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public int getSeatsReserved() {
        return seatsReserved;
    }
}
