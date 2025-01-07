package dev.denismasterherobrine.tripmanager.service;

import dev.denismasterherobrine.tripmanager.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private final List<Client> clients = new ArrayList<>();

    public void addClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        clients.add(client);
    }

    public List<Client> getClients() {
        return new ArrayList<>(clients);
    }
}
