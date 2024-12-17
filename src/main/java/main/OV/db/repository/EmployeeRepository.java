package main.OV.db.repository;

import main.OV.db.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository  extends JpaRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findByRole(String role); // Busca empleados por rol
    List<EmployeeEntity> findAll(); // Obtiene todos los empleados

    Optional<EmployeeEntity> findByEmail(String email);
}
