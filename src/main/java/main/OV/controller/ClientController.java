package main.OV.controller;

import main.OV.dto.ClientDto;
import main.OV.dto.ClientEntryDto;
import main.OV.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IClientService clientService;
    @GetMapping("/getAllClients")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        try {
            List<ClientDto> clients = clientService.getAllClients();

            if (clients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getClientsByDate")
    public ResponseEntity<List<ClientEntryDto>> getClientsByDate(@RequestParam("dateTime") String dateTime) {
        try {
            // Parseamos la fecha y hora recibida como parámetro
            LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime);

            List<ClientEntryDto> clients = clientService.getClientsByDate(parsedDateTime);

            if (clients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getClientsCountByHour")
    public ResponseEntity<List<ClientEntryDto>> getClientsCountByHour() {
        try {
            // Llamamos al servicio para obtener la lista de registros por hora
            List<ClientEntryDto> clientsByHour = clientService.getClientsCountByHour();

            if (clientsByHour.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(clientsByHour, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
