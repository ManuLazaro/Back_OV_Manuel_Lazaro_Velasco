package main.OV.db.entity;

import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "Room")
@Data
public class RoomEntity extends AbstractBaseEntity {
    public RoomEntity(Long roomId, CenterEntity center, String type, Integer capacity, String name) {
        this.roomId = roomId;
        this.center = center;
        this.type = type;
        this.capacity = capacity;
        this.name = name;
    }

    public RoomEntity() {
    }

    /** EL id de la sala */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    /** Nombre de la sala */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /** Capacidad de la sala. */
    @Column(name = "capacity")
    private Integer capacity;

    /** Tipo de sala */
    @Column(name = "type", length = 50)
    private String type;


    /** Centro de la sala. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private CenterEntity center; // Relación con el centro al que pertenece la habitación
}
