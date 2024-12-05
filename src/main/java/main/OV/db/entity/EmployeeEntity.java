package main.OV.db.entity;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Employee")
@Data
public class EmployeeEntity extends AbstractBaseEntity {
    public EmployeeEntity(CenterEntity center, String lastName, String firstName, Long employeeId) {
        this.center = center;
        this.lastName = lastName;
        this.firstName = firstName;
        this.employeeId = employeeId;
    }

    public EmployeeEntity() {
    }

    /** The ID of the employee. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    /** The first name of the employee. */
    @Column(name = "first_name")
    private String firstName;

    /** The last name of the employee. */
    @Column(name = "last_name")
    private String lastName;

    /** The center where the employee works. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private CenterEntity center; // El centro al que pertenece el empleado
}
