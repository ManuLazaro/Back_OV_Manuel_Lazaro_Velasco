package main.OV.controller;

import jakarta.validation.ConstraintViolationException;
import main.OV.db.entity.ClientEntity;
import main.OV.dto.ClientDto;
import main.OV.dto.ClientEntryDto;
import main.OV.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping("/saveClient")
    public ResponseEntity<?> saveClient(@RequestBody ClientEntity client) {
        try {
            ClientEntity newClient = clientService.saveClient(client);
            return new ResponseEntity<>(newClient, HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            String errors = e.getConstraintViolations()
                    .stream()
                    .map(s -> s.getMessage())
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
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

    @PostMapping("/saveClientEntry")
    public ResponseEntity<?> saveClientEntry(@RequestBody Map<String, String> request) {
        try {
            // Obtenemos el email del cuerpo de la solicitud
            String email = request.get("email");

            // Luego proceder con el resto de la lógica como antes
            ClientEntryDto newClientEntry = clientService.saveClientEntry(email);
            return new ResponseEntity<>(newClientEntry, HttpStatus.CREATED);

        } catch (ConstraintViolationException e) {
            String errors = e.getConstraintViolations()
                    .stream()
                    .map(s -> s.getMessage())
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}