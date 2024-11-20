package main.OV.dto;

public class ClientDto {
    private String name;
    private String lastName;
    private String subscription;

    public ClientDto(String name, String lastName, String subscription) {
        this.name = name;
        this.lastName = lastName;
        this.subscription = subscription;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }
}
