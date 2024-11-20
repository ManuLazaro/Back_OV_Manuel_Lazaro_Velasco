package main.OV.db.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "User")
@Data
public class UserEntity extends AbstractBaseEntity {
    public UserEntity(Long userId, String username, String password, String email, Role role, ClientEntity client, EmployeeEntity employee) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.client = client;
        this.employee = employee;
    }

    /** id  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /** nombre del usuario */
    @Column(name = "username", length = 100, unique = true, nullable = false)
    private String username;

    /**  password  */
    @Column(name = "password", nullable = false)
    private String password;

    /**  email  */
    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    /** ROl del usuario (e.g., Admin, Employee, Client). */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee; // Relación con un empleado (si aplica)


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client; // Relación con un cliente (si aplica)

    public UserEntity() {
    }


    public enum Role {
        ADMIN, EMPLOYEE, CLIENT;
    }
}
