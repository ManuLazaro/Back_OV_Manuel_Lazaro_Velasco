package main.OV.dto;

import java.time.LocalDateTime;

public class ClientEntryDto {
    private Long entryId; // ID único para cada entrada/salida

    private int count; // COntador para el Número de registros en esa hora
    private ClientDto client;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    public ClientEntryDto() {
    }

    public ClientEntryDto(int count) {
        this.count = count;
    }

    public ClientEntryDto(Long entryId, ClientDto client, LocalDateTime entryTime, LocalDateTime exitTime) {
        this.entryId = entryId;
        this.client = client;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public ClientEntryDto(Long entryId, ClientDto clientDto, LocalDateTime entryTime, LocalDateTime exitTime, int count) {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
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
}
