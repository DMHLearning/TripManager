package dev.denismasterherobrine.tripmanager.service;

import dev.denismasterherobrine.tripmanager.client.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientTest {

    @Test
    public void testClientCreation() {
        Client client = new Client("John Doe", "john@example.com");
        assertEquals("John Doe", client.getName(), "Client name should be correctly set");
        assertEquals("john@example.com", client.getEmail(), "Client email should be correctly set");
    }

    @Test
    public void testSetPhoneValid() {
        Client client = new Client("Jane Doe", "jane@example.com");
        client.setPhone("+1234567890");
        assertEquals("+1234567890", client.getPhone(), "Phone number should be set correctly");
    }

    @Test
    public void testSetPhoneInvalid() {
        Client client = new Client("Jane Doe", "jane@example.com");
        assertThrows(IllegalArgumentException.class, () -> client.setPhone(null), "Phone number cannot be null");
        assertThrows(IllegalArgumentException.class, () -> client.setPhone(""), "Phone number cannot be empty");
    }

    @Test
    public void testToString() {
        Client client = new Client("Alice", "alice@example.com");
        client.setPhone("+9876543210");
        assertEquals("Client{name='Alice', email='alice@example.com', phone='+9876543210'}", client.toString(), "toString should return correct representation");
    }
}
