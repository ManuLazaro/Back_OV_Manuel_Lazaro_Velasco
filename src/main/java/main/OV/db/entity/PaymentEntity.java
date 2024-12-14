package main.OV.db.entity;


import jakarta.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;

@Entity
@Table(name = "Payments")

public class PaymentEntity extends AbstractBaseEntity {

    /** ID de pago */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    /** Cliente que hace el pago */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "client_id")
//    private ClientEntity client;

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

}
