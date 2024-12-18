package main.OV.controller;

import main.OV.db.entity.ClientEntity;
import main.OV.db.entity.PaymentEntity;
import main.OV.dto.PaymentDto;
import main.OV.service.IClientService;
import main.OV.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private IClientService clientService;

    @PostMapping("/savePayment")
    public ResponseEntity<?> savePayment(@RequestBody PaymentDto paymentDto) {
        try {
            // Obtener el cliente por el correo electrónico
            ClientEntity client = clientService.findByEmail(paymentDto.getEmail());
            if (client == null) {
                return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
            }

            // Crear un nuevo PaymentEntity
            PaymentEntity newPayment = new PaymentEntity();
            newPayment.setAmount(paymentDto.getAmount());
            newPayment.setMethod(paymentDto.getMethod());
            newPayment.setPaymentDate(LocalDate.now());
            newPayment.setStatus("completed");
            newPayment.setClient(client);

            // Guardar el pago en la base de datos
            PaymentEntity savedPayment = paymentService.savePayment(newPayment);

            return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar el pago: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllPayments")
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        try {
            List<PaymentDto> payments = paymentService.getAllPayments();
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getPaymentsByDate", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentDto>> getPaymentsByDate() {
        try {
            return new ResponseEntity<>(paymentService.getPaymentsByDate(), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
