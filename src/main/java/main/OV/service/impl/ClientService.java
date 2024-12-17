package main.OV.service.impl;

import main.OV.db.entity.ClientEntity;
import main.OV.db.entity.ClientEntryEntity;
import main.OV.db.repository.ClientEntryRepository;
import main.OV.db.repository.ClientRepository;
import main.OV.dto.ClientDto;
import main.OV.dto.ClientEntryDto;
import main.OV.service.IClientService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private ClientEntryRepository clientEntryRepository;

    public ClientService(ClientRepository clientRepository, ClientEntryRepository clientEntryRepository) {
        this.clientRepository = clientRepository;
        this.clientEntryRepository = clientEntryRepository;
    }
    /**
     * @return
     */
    @Override
    public List<ClientDto> getAllClients() {
        // Obtiene los empleados con rol "monitor"
        List<ClientEntity> clients = clientRepository.findAll();

        return clients.stream().map(client -> {
            ClientDto clientDto = new ClientDto();

            clientDto.setFirstName(client.getFirstName());
            clientDto.setLastName(client.getLastName());
            clientDto.setCreationDate(client.getCreatedAt());
            clientDto.setSubscription(client.getSubscription());
            clientDto.setStatus(client.getStatus());


            return clientDto;
        }).collect(Collectors.toList());
    }

    /**
     * @param parsedDateTime
     * @return
     */
    @Override
    public List<ClientEntryDto> getClientsByDate(LocalDateTime dateTime) {
        // Calcular el inicio y el final de la hora
        LocalDateTime startOfHour = dateTime.withMinute(0).withSecond(0).withNano(0); // Hora exacta, minuto 00
        LocalDateTime endOfHour = startOfHour.plusMinutes(59).withSecond(59).withNano(999999999); // Hora exacta, minuto 59

        // Buscar entradas que estén dentro del rango de la hora especificada y que no hayan salido o salgan después de startOfHour
        List<ClientEntryEntity> entries = clientEntryRepository.findByEntryTimeBetween(startOfHour, endOfHour);

        // Agregar entradas con exit_time NULL o posterior a startOfHour
        entries.addAll(clientEntryRepository.findByExitTimeIsNullOrExitTimeAfter(startOfHour));

        // Convertir las entidades a DTO
        return entries.stream()
                .map(entry -> new ClientEntryDto(
                        entry.getEntryId(),
                        new ClientDto(
                                entry.getClient().getFirstName(),
                                entry.getClient().getLastName(),
                                entry.getClient().getSubscription(),
                                entry.getClient().getStatus(),
                                entry.getClient().getCreatedAt()
                        ),
                        entry.getEntryTime(),
                        entry.getExitTime()))
                .collect(Collectors.toList());
    }

    /**
     * @return
     */
    @Override
    public List<ClientEntryDto> getClientsCountByHour() {
        // Definir el inicio y el fin del rango de horas: de 7:00 a 23:00
        LocalDateTime startOfDay = LocalDateTime.now().withHour(7).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDay = LocalDateTime.now().withHour(23).withMinute(0).withSecond(0).withNano(0);

        // Lista para almacenar el conteo de clientes por hora
        List<ClientEntryDto> clientEntriesByHour = new ArrayList<>();

        // Iteramos por cada hora desde las 7:00 hasta las 23:00
        for (LocalDateTime currentHour = startOfDay; currentHour.isBefore(endOfDay.plusHours(1)); currentHour = currentHour.plusHours(1)) {
            // El rango de la hora: de 'currentHour' a 'currentHour + 1 hora'
            LocalDateTime startOfHour = currentHour;
            LocalDateTime endOfHour = startOfHour.plusHours(1).minusNanos(1); // Fin de la hora, justo antes de la siguiente hora

            // Buscar clientes que entraron antes de esta hora y aún no han salido o han salido después de esta hora
            List<ClientEntryEntity> entriesInHour = clientEntryRepository.findByEntryTimeBeforeAndExitTimeAfter(startOfHour, endOfHour);

            // Crear un DTO para la hora actual con el conteo de registros
            ClientEntryDto entryDto = new ClientEntryDto();
            entryDto.setEntryId((long) currentHour.getHour()); // Hora
            entryDto.setCount(entriesInHour.size()); // Cantidad de registros de clientes en esa hora

            // Agregar el DTO a la lista
            clientEntriesByHour.add(entryDto);
        }

        // Retornar la lista con los registros por hora
        return clientEntriesByHour;
    }




}
