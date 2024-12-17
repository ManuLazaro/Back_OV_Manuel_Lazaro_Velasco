package main.OV.service.impl;

import main.OV.db.entity.CenterEntity;
import main.OV.db.repository.ICenterRepository;
import main.OV.dto.ClientDto;
import main.OV.service.ICenterService;
import main.OV.util.Constants;

import java.util.List;

public class CenterService implements ICenterService, Constants {

    private ICenterRepository centerRepository;

    @Override
    public CenterEntity getCenterInfoById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<ClientDto> getClientsByCenterId(Long centerId) throws Exception {
        return null;
    }


}
