package main.OV.service.impl;

import main.OV.db.entity.RegisterEntity;
import main.OV.dto.RegisterDto;
import main.OV.service.IRegisterService;
import main.OV.util.Constants;

import java.util.Collections;
import java.util.List;

public class RegisterService implements IRegisterService, Constants {

    /**
     * @return Todos los registros de la semana
     * Registros por hora de cada dia de la semana anterior
     */
    @Override
    public List<RegisterDto> getAllWeekRegisters() {
        return Collections.emptyList();
    }

    /**
     * @param register
     * @return
     */
    @Override
    public RegisterEntity saveRegister(RegisterEntity register) {
        return null;
    }
}
