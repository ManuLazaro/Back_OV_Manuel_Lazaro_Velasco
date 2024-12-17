package main.OV.service;

import main.OV.db.entity.ShiftEntity;
import main.OV.dto.ShiftDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IShiftService {
    ShiftEntity saveShift(ShiftEntity shift);

    List<ShiftDto> getAllShift();

    List<ShiftDto> getShift();

    ShiftEntity editShift(ShiftEntity shift);

    List<ShiftDto> getShiftByDate();
}
