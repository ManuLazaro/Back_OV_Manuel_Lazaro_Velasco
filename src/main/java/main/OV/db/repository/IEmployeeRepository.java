package main.OV.db.repository;


import main.OV.db.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query("SELECT e.password FROM EmployeeEntity e WHERE e.email = :email")
    Optional<String> findPasswordByEmail(@Param("email") String email);
}
