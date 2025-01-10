package dev.denismasterherobrine.tripmanager;

import com.google.gson.Gson;
import dev.denismasterherobrine.tripmanager.booking.Booking;
import dev.denismasterherobrine.tripmanager.booking.BookingMicroservice;
import dev.denismasterherobrine.tripmanager.booking.BookingRequest;
import dev.denismasterherobrine.tripmanager.client.Client;
import dev.denismasterherobrine.tripmanager.client.ClientMicroservice;
import dev.denismasterherobrine.tripmanager.tour.Tour;
import dev.denismasterherobrine.tripmanager.tour.TourMicroservice;

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
            // Get request data
            System.out.println(res.body());
            if (req.body().isEmpty()) {
                res.status(400);
                return gson.toJson("Request body is empty! Is the frontend error not being catched?");
            }

            Client client = gson.fromJson(req.body(), Client.class);
            clientService.addClient(client.getName(), client.getEmail(), client.getPhone());
            res.status(201);
            return gson.toJson(client);
        });

        // Получить всех клиентов
        get("/clients", (req, res) -> {
            // Get request data
            System.out.println(res.body());
            if (req.body().isEmpty()) {
                res.status(400);
                return gson.toJson("Request body is empty! Is the frontend error not being catched?");
            }

            List<Client> clients = clientService.getClients();
            res.type("application/json");
            return gson.toJson(clients);
        });

        // Удалить клиента
        delete("/clients/:email", (req, res) -> {
            // Get request data
            System.out.println(res.body());
            if (req.body().isEmpty()) {
                res.status(400);
                return gson.toJson("Request body is empty! Is the frontend error not being catched?");
            }

            String email = req.params(":email");
            boolean removed = clientService.removeClient(email);
            res.status(removed ? 200 : 404);
            return gson.toJson("Client removed: " + removed);
        });

        // Добавить бронирование
        post("/bookings", (req, res) -> {
            // Get request data
            System.out.println(res.body());
            if (req.body().isEmpty()) {
                res.status(400);
                return gson.toJson("Request body is empty! Is the frontend error not being catched?");
            }
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
            // Get request data
            System.out.println(res.body());
            if (req.body().isEmpty()) {
                res.status(400);
                return gson.toJson("Request body is empty! Is the frontend error not being catched?");
            }

            List<Booking> bookings = bookingService.getBookings();
            res.type("application/json");
            return gson.toJson(bookings);
        });

        // Отмена бронирования
        delete("/bookings", (req, res) -> {
            // Get request data
            System.out.println(res.body());
            if (req.body().isEmpty()) {
                res.status(400);
                return gson.toJson("Request body is empty! Is the frontend error not being catched?");
            }

            Booking booking = gson.fromJson(req.body(), Booking.class);
            boolean canceled = bookingService.cancelBooking(booking.getClient(), booking.getTour());
            res.status(canceled ? 200 : 404);
            return gson.toJson("Booking canceled: " + canceled);
        });

        // Добавить тур
        post("/tours", (req, res) -> {
            // Get request data
            System.out.println(res.body());
            if (req.body().isEmpty()) {
                res.status(400);
                return gson.toJson("Request body is empty! Is the frontend error not being catched?");
            }

            Tour tour = gson.fromJson(req.body(), Tour.class);
            tourService.addTour(tour.getName(), tour.getDescription(), tour.getPrice(), tour.getDuration(), tour.getSeats());
            res.status(201);
            return gson.toJson(tour);
        });

        // Получить все туры
        get("/tours", (req, res) -> {
            // Get request data
            System.out.println(res.body());
            if (req.body().isEmpty()) {
                res.status(400);
                return gson.toJson("Request body is empty! Is the frontend error not being catched?");
            }

            List<Tour> tours = tourService.getTours();
            res.type("application/json");
            return gson.toJson(tours);
        });

        // Удалить тур
        delete("/tours/:name", (req, res) -> {
            // Get request data
            System.out.println(res.body());
            if (req.body().isEmpty()) {
                res.status(400);
                return gson.toJson("Request body is empty! Is the frontend error not being catched?");
            }

            String name = req.params(":name");

            List<Tour> tours = tourService.getTours();
            for (Tour tour : tours) {
                if (tour.getName().equals(name)) {
                    bookingService.getBookings().removeIf(booking -> booking.getTour().equals(tour));
                    tours.remove(tour);
                    System.out.println("Tour removed with related bookings: " + name);
                    res.status(200);
                    return gson.toJson("Tour removed: " + name);
                }
            }
            res.status(404);
            return gson.toJson("Tour not found: " + name);
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
