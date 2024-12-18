package main.OV.service;

import main.OV.db.entity.PaymentEntity;
import main.OV.dto.PaymentDto;

import java.util.List;


public interface IPaymentService {
    PaymentEntity savePayment(PaymentEntity payment);

    List<PaymentDto> getAllPayments();

    List<PaymentDto> getPaymentsByDate();
}
