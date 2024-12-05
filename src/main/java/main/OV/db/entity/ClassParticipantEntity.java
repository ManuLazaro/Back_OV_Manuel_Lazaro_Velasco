package main.OV.db.entity;

import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "Class_Participant")
@Data
public class ClassParticipantEntity extends AbstractBaseEntity {



    /** Id del cliente que va a la clase. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    /** fecha y hora de la clase */
    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;
}
