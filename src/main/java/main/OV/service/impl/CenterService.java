package main.OV.service.impl;

import main.OV.db.entity.CenterEntity;
import main.OV.db.repository.ICenterRepository;
import main.OV.dto.ClientDto;
import main.OV.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CenterService implements ICenterService, Constants {
  //  @Autowired
    private ICenterRepository centerRepository;

    @Override
    public CenterEntity getCenterInfoById(Long id) throws Exception {

//        CenterEntity center = centerRepository.getHistoryByBuilding(id);
//        if (center == null) {
//            throw new Exception("Center not found with id: " + id);
//        }
        return  null;
    }

    @Override
    public List<ClientDto> getClientsByCenterId(Long centerId) throws Exception {
//        List<ClientDto> clients = centerRepository.findClientsByCenterId(centerId);
//        if (clients == null || clients.isEmpty()) {
//            throw new Exception("No clients found for center with id: " + centerId);
//        }
        return null;
    }
}
