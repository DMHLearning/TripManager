package dev.denismasterherobrine.tripmanager.service;

import dev.denismasterherobrine.tripmanager.model.Client;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {

    @Test
    public void testAddAndGetClients() {
        ClientService clientService = new ClientService();
        Client client1 = new Client("Frank", "frank@example.com");
        Client client2 = new Client("Grace", "grace@example.com");

        clientService.addClient(client1);
        clientService.addClient(client2);

        List<Client> clients = clientService.getClients();
        assertEquals(2, clients.size(), "Client list size should be 2");
        assertTrue(clients.contains(client1), "Client list should contain client1");
        assertTrue(clients.contains(client2), "Client list should contain client2");
    }

    @Test
    public void testAddNullClient() {
        ClientService clientService = new ClientService();
        assertThrows(IllegalArgumentException.class, () -> clientService.addClient(null), "Adding null client should throw exception");
    }
}

