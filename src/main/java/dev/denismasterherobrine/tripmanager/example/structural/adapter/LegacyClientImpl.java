package dev.denismasterherobrine.tripmanager.example.structural.adapter;

public class LegacyClientImpl implements ILegacyClient {
    @Override
    public String getClientInfo() {
        return "John Doe, john@example.com";
    }
}