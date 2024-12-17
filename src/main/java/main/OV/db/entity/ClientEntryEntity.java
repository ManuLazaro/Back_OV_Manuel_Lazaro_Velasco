package main.OV.db.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "client_entries")
public class ClientEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_id")
    private Long entryId; // ID único para cada entrada/salida

    @ManyToOne(fetch = FetchType.LAZY) // Relación con la entidad Cliente
    @JoinColumn(name = "client_id", nullable = false) // Referencia al client_id
    private ClientEntity client;

    @Column(name = "entry_time", nullable = false)
    private LocalDateTime entryTime; // Fecha y hora de entrada

    @Column(name = "exit_time")
    private LocalDateTime exitTime; // Fecha y hora de salida (puede ser NULL inicialmente)

    public ClientEntryEntity(Long entryId, ClientEntity client, LocalDateTime entryTime, LocalDateTime exitTime) {
        this.entryId = entryId;
        this.client = client;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public ClientEntryEntity() {

    }
// Getters y setters

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    @Override
    public String toString() {
        return "ClientEntry{" +
                "entryId=" + entryId +
                ", client=" + client +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                '}';
    }
}
