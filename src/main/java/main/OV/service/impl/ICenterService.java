package main.OV.service.impl;

import main.OV.db.entity.CenterEntity;
import main.OV.dto.ClientDto;

import java.util.List;

public interface ICenterService {

    CenterEntity getCenterInfoById(Long id) throws Exception;


    List<ClientDto> getClientsByCenterId(Long centerId) throws Exception;

}
