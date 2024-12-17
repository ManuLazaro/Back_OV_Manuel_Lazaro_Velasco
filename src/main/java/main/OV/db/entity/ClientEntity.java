package main.OV.db.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Client")

public class ClientEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "client_id")
        private Long clientId;

//        @ManyToOne
//        @JoinColumn(name = "center_id", referencedColumnName = "center_id", nullable = true)
//        private CenterEntity center;

        @Column(name = "first_name", nullable = false, length = 100)
        private String firstName;

        @Column(name = "last_name", nullable = false, length = 100)
        private String lastName;

        @Column(name = "email", nullable = true, length = 100, unique = true)
        private String email;

        @Column(name = "phone", nullable = true, length = 15)
        private String phone;
        @Column(name = "subscription", length = 15)
        private String subscription;
        @Column(name = "status", length = 20, nullable = true)
        private String status = "active";

        @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        private LocalDateTime createdAt;

        @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        private LocalDateTime updatedAt;

        // Constructor vacío
        public ClientEntity() {
        }

        public String getSubscription() {
                return subscription;
        }

        public void setSubscription(String subscription) {
                this.subscription = subscription;
        }

        public Long getClientId() {
                return clientId;
        }

        public void setClientId(Long clientId) {
                this.clientId = clientId;
        }

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

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public LocalDateTime getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
                this.createdAt = createdAt;
        }

        public LocalDateTime getUpdatedAt() {
                return updatedAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
                this.updatedAt = updatedAt;
        }
}
