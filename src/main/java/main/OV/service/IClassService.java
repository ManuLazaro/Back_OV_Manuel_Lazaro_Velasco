package main.OV.service;

import main.OV.db.entity.ClassEntity;
import main.OV.dto.ClassDto;

import java.util.List;

public interface IClassService {
    ClassEntity saveClass(ClassEntity clase);

    List<ClassDto> getAllClasses();

    List<ClassDto> getClassByDate();

    void deleteApartament(Long classId);
}
