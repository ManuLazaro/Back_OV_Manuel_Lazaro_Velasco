package main.OV.db.entity;

import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;

@Entity
@Table(name = "Subscriptions")
@Data
public class SubscriptionEntity extends AbstractBaseEntity {

    /** The ID of the subscription. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long subscriptionId;

    /** The client who is subscribing. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client; // Relación con el cliente

    /** The center where the subscription is applied. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private CenterEntity center; // Relación con el centro

    /** The start date of the subscription. */
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    /** The end date of the subscription. */
    @Column(name = "end_date")
    private LocalDate endDate;

    /** The status of the subscription. */
    @Column(name = "status", length = 20, nullable = false)
    private String status;

}
