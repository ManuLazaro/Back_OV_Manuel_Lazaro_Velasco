package main.OV.service;

import main.OV.db.entity.ClientEntity;
import main.OV.dto.ClientDto;
import main.OV.dto.ClientEntryDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IClientService {
    List<ClientDto> getAllClients();

    List<ClientEntryDto> getClientsByDate(LocalDateTime parsedDateTime);

    List<ClientEntryDto> getClientsCountByHour();

    ClientEntity saveClient(ClientEntity client);

    ClientEntity findByEmail(String email);

    ClientEntryDto saveClientEntry(String email);
}
