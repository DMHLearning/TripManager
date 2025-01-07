package dev.denismasterherobrine.tripmanager.example.structural.adapter;

import dev.denismasterherobrine.tripmanager.model.Client;

public class ClientAdapter extends Client {
    private final ILegacyClient legacyClient;

    public ClientAdapter(ILegacyClient legacyClient) {
        super("", "");
        this.legacyClient = legacyClient;
        adaptClientInfo();
    }

    private void adaptClientInfo() {
        String[] parts = legacyClient.getClientInfo().split(", ");
        this.setPhone(parts[1]);
        this.setName(parts[0]);
    }
}
