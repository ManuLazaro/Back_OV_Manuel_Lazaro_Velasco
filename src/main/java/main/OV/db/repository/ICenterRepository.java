package main.OV.db.repository;

import main.OV.db.entity.CenterEntity;
import main.OV.dto.ClientDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICenterRepository extends IBaseRepository<CenterEntity, Long>  {

//    @Query("SELECT c.name, c.address, c.phone " +
//            "FROM CenterEntity c " +
//            "WHERE c.id = :id")
//    CenterEntity getHistoryByBuilding(Long id);
//
//    @Query("SELECT c.name, c.lastName, c.subscription " +
//            "FROM ClientEntity c " +
//            "WHERE c.centerId = :centerId")
//    List<ClientDto> findClientsByCenterId(Long centerId);
}
