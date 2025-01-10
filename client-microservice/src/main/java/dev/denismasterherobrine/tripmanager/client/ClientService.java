package dev.denismasterherobrine.tripmanager.client;

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
