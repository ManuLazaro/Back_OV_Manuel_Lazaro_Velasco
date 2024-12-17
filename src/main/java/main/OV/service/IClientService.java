package main.OV.service;

import main.OV.dto.ClientDto;
import main.OV.dto.ClientEntryDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IClientService {
    List<ClientDto> getAllClients();

    List<ClientEntryDto> getClientsByDate(LocalDateTime parsedDateTime);

    List<ClientEntryDto> getClientsCountByHour();
}
