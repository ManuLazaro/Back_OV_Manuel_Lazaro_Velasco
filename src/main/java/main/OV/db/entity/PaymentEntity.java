package main.OV.db.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Payments")

public class PaymentEntity {

    /** ID de pago */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    /** Cliente que hace el pago */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    /** Cantidad del pago */
    @Column(name = "amount", nullable = false)
    private Double amount;

    /** The payment date. */
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    /** Metodo de pago ( cash, credit card). */
    @Column(name = "method", length = 50)
    private String method;

    /** esatdo del pago (completed, pending). */
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    public PaymentEntity() {

    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
