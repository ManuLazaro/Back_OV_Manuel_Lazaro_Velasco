package main.OV.db.repository;

import main.OV.db.entity.ClientEntity;
import main.OV.db.entity.ClientEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientEntryRepository extends JpaRepository<ClientEntryEntity, Long> {

    List<ClientEntryEntity> findByEntryTimeBetween(LocalDateTime startOfHour, LocalDateTime endOfHour);

    Collection<? extends ClientEntryEntity> findByExitTimeIsNullOrExitTimeAfter(LocalDateTime startOfHour);

    @Query("SELECT e FROM ClientEntryEntity e WHERE e.entryTime <= :endOfHour " +
            "AND (e.exitTime IS NULL OR e.exitTime >= :startOfHour)")
    List<ClientEntryEntity> findByEntryTimeBeforeAndExitTimeAfter(
            @Param("startOfHour") LocalDateTime startOfHour,
            @Param("endOfHour") LocalDateTime endOfHour);


    // Encuentra una entrada para un cliente con un entryTime después de medianoche del día actual
    Optional<ClientEntryEntity> findByClientAndEntryTimeIsAfter(ClientEntity client, LocalDateTime startOfDay);

    // Cuenta cuántas entradas tiene el cliente en el día de hoy
    int countByClientAndEntryTimeIsAfter(ClientEntity client, LocalDateTime startOfDay);



}
