package main.OV.service.impl;

import main.OV.db.entity.ShiftEntity;
import main.OV.dto.ShiftDto;
import main.OV.service.IShiftService;
import main.OV.util.Constants;

import java.util.Collections;
import java.util.List;

public class ShiftService implements IShiftService, Constants {


    /**
     * @param shift
     * @return
     */
    @Override
    public ShiftEntity saveShift(ShiftEntity shift) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<ShiftDto> getAllShift() {
        return Collections.emptyList();
    }

    /**
     * @return
     */
    @Override
    public List<ShiftDto> getShift() {
        return Collections.emptyList();
    }

    /**
     * @param shift
     * @return
     */
    @Override
    public ShiftEntity editShift(ShiftEntity shift) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<ShiftDto> getShiftByDate() {
        return Collections.emptyList();
    }

}
