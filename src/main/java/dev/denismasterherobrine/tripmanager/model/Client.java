package dev.denismasterherobrine.tripmanager.model;

public class Client {
    private String name;
    private String email;
    private String phone;

    public Client(String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be null or empty");
        }

        if (email == null || email.trim().isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be null or empty");
        }

        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty() || !phone.matches("^\\+[1-9][0-9]{0,2}[1-9][0-9]{6,7}$")) {
            throw new IllegalArgumentException("Phone number must start with a country code (+), followed by a valid city code and 7 digits.");
        }

        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{name='" + name + "', email='" + email + "', phone='" + phone + "'}";
    }
}

