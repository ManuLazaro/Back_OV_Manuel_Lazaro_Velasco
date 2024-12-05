package main.OV.db.entity;

import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Client")
@Data
public class ClientEntity extends AbstractBaseEntity {

    /**  id  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /** nombre  */
    @Column(name = "name", length = 100, unique = true, nullable = false)
    private String name;
    /** nombre  */
    @Column(name = "last_name", length = 100, unique = true, nullable = false)
    private String lastName;
    /**  email  */
    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "center_id")
    private String centerId;

    /** subscription */
    @Enumerated(EnumType.STRING)
    @Column(name = "subscription", length = 20, nullable = false)
    private Role subscription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee; // Relación con un empleado (si aplica)



    public enum Role {
        ADMIN, EMPLOYEE, CLIENT;
    }
}
