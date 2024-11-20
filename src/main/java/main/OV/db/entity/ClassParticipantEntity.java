package main.OV.db.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Class_Participant")
@Data
public class ClassParticipantEntity extends AbstractBaseEntity {

    /** Id de la clase  */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    /** Id del cliente que va a la clase. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    /** fecha y hora de la clase */
    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;
}
