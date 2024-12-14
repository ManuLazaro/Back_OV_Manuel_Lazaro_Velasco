package main.OV.db.entity;


import jakarta.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "Inventory_Log")

public class InventoryLogEntity extends AbstractBaseEntity {

    /** The ID of the inventory log. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_log_id")
    private Long inventoryLogId;

    /** The equipment being logged. */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "equipment_id")
//    private EquipmentEntity equipment; // Relación con el equipo

    /** The action performed (e.g., added, removed). */
    @Column(name = "action", length = 50, nullable = false)
    private String action;

    /** The quantity of equipment involved in the action. */
    @Column(name = "quantity")
    private Integer quantity;

    /** The employee who performed the action. */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "performed_by")
//    private EmployeeEntity performedBy; // Relación con el empleado que realizó la acción

    /** The timestamp of the action. */
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
}
