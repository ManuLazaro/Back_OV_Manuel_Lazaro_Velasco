package main.OV.controller;

import jakarta.validation.ConstraintViolationException;
import main.OV.db.entity.PaymentEntity;
import main.OV.dto.PaymentDto;
import main.OV.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/doPayment")
    public ResponseEntity<?> doPayment(@RequestBody PaymentEntity payment) {
        try {

            PaymentEntity newPayment = paymentService.doPayment(payment);

            return new ResponseEntity<>(newPayment, HttpStatus.CREATED);

        } catch (ConstraintViolationException e) {
            String errors = e.getConstraintViolations().stream().map(s -> s.getMessage()).collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
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
