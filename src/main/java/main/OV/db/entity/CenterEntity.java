package main.OV.db.entity;

import jakarta.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Center")

// @Audited
public class CenterEntity extends AbstractBaseEntity{
    public CenterEntity() {
    }

    public CenterEntity(Long ownerId, String name, String address, Set<EmployeeEntity> employees, Set<SubscriptionEntity> subscriptions, String phone, String email, EmployeeEntity owner, Set<RoomEntity> rooms, Set<ClientEntity> clients, Set<ClassEntity> classes, Set<EquipmentEntity> equipment) {
        this.ownerId = ownerId;
        this.name = name;
        this.address = address;
//        this.employees = employees;
//        this.subscriptions = subscriptions;
        this.phone = phone;
        this.email = email;
//        this.owner = owner;
//        this.rooms = rooms;
//        this.clients = clients;
//        this.classes = classes;
//        this.equipment = equipment;
    }

    @Column(name = "owner_id")
    private Long ownerId; // propietario


    @Column(name = "name", length = 100)
    private String name; // Nombre del centro

    @Column(name = "address")
    private String address; // Dirección del centro

    @Column(name = "phone")
    private String phone; // Teléfono del centro

    @Column(name = "email")
    private String email; // Email del centro

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "owner_id", referencedColumnName = "employee_id", insertable = false, updatable = false)
//    private EmployeeEntity owner;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center")
//    private Set<EmployeeEntity> employees = new HashSet<>(0);
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center")
//    private Set<RoomEntity> rooms = new HashSet<>(0);
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center")
//    private Set<ClientEntity> clients = new HashSet<>(0);
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center")
//    private Set<ClassEntity> classes = new HashSet<>(0);

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center")
//    private Set<EquipmentEntity> equipment = new HashSet<>(0);
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center")
//    private Set<SubscriptionEntity> subscriptions = new HashSet<>(0);
}


