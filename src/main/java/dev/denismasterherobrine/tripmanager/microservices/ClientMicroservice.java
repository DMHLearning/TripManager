package dev.denismasterherobrine.tripmanager.microservices;

import dev.denismasterherobrine.tripmanager.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientMicroservice {
    private final List<Client> clients = new ArrayList<>();

    // Добавление клиента
    public void addClient(String name, String email, String phone) {
        Client client = new Client(name, email);
        client.setPhone(phone);
        clients.add(client);
        System.out.println("Client added: " + client);
    }

    // Получение всех клиентов
    public List<Client> getClients() {
        return new ArrayList<>(clients);
    }

    // Поиск клиента по email
    public Optional<Client> findClientByEmail(String email) {
        return clients.stream().filter(client -> client.getEmail().equals(email)).findFirst();
    }

    // Удаление клиента
    public boolean removeClient(String email) {
        Optional<Client> client = findClientByEmail(email);
        if (client.isPresent()) {
            clients.remove(client.get());
            System.out.println("Client removed: " + client.get());
            return true;
        }
        System.out.println("Client not found with email: " + email);
        return false;
    }
}

