package dev.denismasterherobrine.tripmanager;

import com.google.gson.Gson;
import dev.denismasterherobrine.tripmanager.microservices.BookingMicroservice;
import dev.denismasterherobrine.tripmanager.microservices.ClientMicroservice;
import dev.denismasterherobrine.tripmanager.microservices.TourMicroservice;
import dev.denismasterherobrine.tripmanager.model.Booking;
import dev.denismasterherobrine.tripmanager.model.BookingRequest;
import dev.denismasterherobrine.tripmanager.model.Client;
import dev.denismasterherobrine.tripmanager.model.Tour;

import java.util.List;
import java.util.Optional;

import static spark.Spark.*;

public class Main {
    private static final ClientMicroservice clientService = new ClientMicroservice();
    private static final BookingMicroservice bookingService = new BookingMicroservice();
    private static final TourMicroservice tourService = new TourMicroservice();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        port(8080);

        enableCORS("*", "*", "*"); // Разрешаем CORS для всех доменов и методов

        // Добавить клиента
        post("/clients", (req, res) -> {
            Client client = gson.fromJson(req.body(), Client.class);
            clientService.addClient(client.getName(), client.getEmail(), client.getPhone());
            res.status(201);
            return gson.toJson(client);
        });

        // Получить всех клиентов
        get("/clients", (req, res) -> {
            List<Client> clients = clientService.getClients();
            res.type("application/json");
            return gson.toJson(clients);
        });

        // Удалить клиента
        delete("/clients/:email", (req, res) -> {
            String email = req.params(":email");
            boolean removed = clientService.removeClient(email);
            res.status(removed ? 200 : 404);
            return gson.toJson("Client removed: " + removed);
        });

        // Добавить бронирование
        post("/bookings", (req, res) -> {
            System.out.println(req.body());

            BookingRequest request = gson.fromJson(req.body(), BookingRequest.class);

            Optional<Client> clientOpt = clientService.findClientByEmail(request.getEmail());
            Optional<Tour> tourOpt = tourService.getTours()
                    .stream()
                    .filter(t -> t.getName().equals(request.getTourName()))
                    .findFirst();

            if (clientOpt.isPresent() && tourOpt.isPresent()) {
                bookingService.addBooking(clientOpt.get(), tourOpt.get(), request.getBookingDate(), request.getSeatsReserved());
                res.status(201);
                return gson.toJson("Booking created successfully!");
            } else {
                res.status(404);
                return gson.toJson("Client or Tour not found.");
            }
        });


        // Получить все бронирования
        get("/bookings", (req, res) -> {
            List<Booking> bookings = bookingService.getBookings();
            res.type("application/json");
            return gson.toJson(bookings);
        });

        // Отмена бронирования
        delete("/bookings", (req, res) -> {
            Booking booking = gson.fromJson(req.body(), Booking.class);
            boolean canceled = bookingService.cancelBooking(booking.getClient(), booking.getTour());
            res.status(canceled ? 200 : 404);
            return gson.toJson("Booking canceled: " + canceled);
        });

        // Добавить тур
        post("/tours", (req, res) -> {
            Tour tour = gson.fromJson(req.body(), Tour.class);
            tourService.addTour(tour.getName(), tour.getDescription(), tour.getPrice(), tour.getDuration(), tour.getSeats());
            res.status(201);
            return gson.toJson(tour);
        });

        // Получить все туры
        get("/tours", (req, res) -> {
            List<Tour> tours = tourService.getTours();
            res.type("application/json");
            return gson.toJson(tours);
        });

        // Удалить тур
        delete("/tours/:name", (req, res) -> {
            String name = req.params(":name");
            boolean removed = tourService.removeTourWithRelatedBookings(name, bookingService);
            res.status(removed ? 200 : 404);
            return gson.toJson("Tour removed: " + removed);
        });

        System.out.println("API Server running on http://localhost:8080");
    }

    // приехали, костыль
    private static void enableCORS(String origin, String methods, String headers) {
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
        });
    }

}
