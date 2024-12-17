package main.OV.dto;

import java.time.LocalDateTime;

public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String subscription;

    private String status;

    private LocalDateTime creationDate;

    public ClientDto() {
    }

    public ClientDto(String firstName, String lastName, String subscription, String status, LocalDateTime creationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subscription = subscription;
        this.status = status;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    // Getters y Setters


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }
}
