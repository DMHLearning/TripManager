package dev.denismasterherobrine.tripmanager.example.behavioral.observer;

public class ClientObserver implements IObserver {
    private String name;

    public ClientObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received notification: " + message);
    }
}

