package main.OV.service.impl;

import main.OV.db.entity.ClientEntity;
import main.OV.db.entity.PaymentEntity;
import main.OV.db.repository.PaymentRepository;
import main.OV.dto.ClientDto;
import main.OV.dto.PaymentDto;
import main.OV.service.IPaymentService;
import main.OV.util.Constants;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PaymentService implements IPaymentService, Constants {
    private final PaymentRepository paymentRepository;

    // Inyección de la dependencia a través del constructor
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * @param payment
     * @return
     */
    @Override
    public PaymentEntity doPayment(PaymentEntity payment) {
        return null;
    }

    /**
     * @return
     *
     */
    @Override
    public List<PaymentDto> getAllPayments() {
        List<PaymentEntity> payments = paymentRepository.findAll();

        return payments.stream().map(payment -> {
            PaymentDto paymentDto = new PaymentDto();

            ClientEntity clientEntity = payment.getClient();
            ClientDto clientDto = new ClientDto(
                    clientEntity.getFirstName(),
                    clientEntity.getLastName(),
                    clientEntity.getSubscription(),
                    clientEntity.getStatus(),
                    clientEntity.getCreatedAt()
            );
            // Asignar el ClientDto al PaymentDto
            paymentDto.setClientDto(clientDto);

            // Rellenar el resto de los campos de PaymentDto
            paymentDto.setPaymentDate(payment.getPaymentDate());
            paymentDto.setAmount(payment.getAmount());
            paymentDto.setMethod(payment.getMethod());
            paymentDto.setStatus(payment.getStatus());
            return paymentDto;
        }).collect(Collectors.toList());
    }


    /**
     * @return
     */
    @Override
    public List<PaymentDto> getPaymentsByDate() {
        return Collections.emptyList();
    }
}
