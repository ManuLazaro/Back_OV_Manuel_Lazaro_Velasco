package main.OV.service.impl;

import main.OV.db.entity.ClassEntity;
import main.OV.dto.ClassDto;
import main.OV.service.IClassService;
import main.OV.util.Constants;

import java.util.Collections;
import java.util.List;

public class ClassService implements IClassService, Constants {
    /**
     * @param clase
     * @return
     */
    @Override
    public ClassEntity saveClass(ClassEntity clase) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<ClassDto> getAllClasses() {
        return Collections.emptyList();
    }

    /**
     * @return
     */
    @Override
    public List<ClassDto> getClassByDate() {
        return Collections.emptyList();
    }

    /**
     * @param classId
     */
    @Override
    public void deleteApartament(Long classId) {

    }
}
