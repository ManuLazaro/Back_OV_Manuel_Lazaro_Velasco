package main.OV.db.entity;


import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Equipment")
@Data
public class EquipmentEntity extends AbstractBaseEntity {
    public EquipmentEntity() {
    }

    public EquipmentEntity(Long equipmentId, CenterEntity center, String name, String status, LocalDateTime createdAt, String type) {
        this.equipmentId = equipmentId;
        this.center = center;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.type = type;
    }

    /** Id del equipo */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long equipmentId;

    /** Centro donde esta el id */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private CenterEntity center; // Relación con el centro

    /** Nombre. */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /** Tipo. */
    @Column(name = "type", length = 50)
    private String type;

    /** Estado del equipo (roto, operativo, dañado) */
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    /** The creation timestamp of the equipment record. */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


}
