package main.OV.service;

import main.OV.db.entity.RegisterEntity;
import main.OV.dto.RegisterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRegisterService { 
    List<RegisterDto> getAllWeekRegisters();

    RegisterEntity saveRegister(RegisterEntity register);
}
