package main.OV.controller;

import jakarta.validation.ConstraintViolationException;
import main.OV.db.entity.ClassEntity;
import main.OV.dto.ClassDto;
import main.OV.service.IClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

public class ClassController {

    private IClassService classService;

    @RequestMapping(value = "/class/saveClass", method = RequestMethod.POST)
    public ResponseEntity<?> saveClass(@RequestBody ClassEntity clase) {
        try {
            ClassEntity newClass = classService.saveClass(clase);

            return new ResponseEntity<>(newClass, HttpStatus.CREATED);

        } catch (ConstraintViolationException e) {
            String errors = e.getConstraintViolations().stream().map(s -> s.getMessage()).collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/class/getAllClasses", method = RequestMethod.GET)
    public ResponseEntity<List<ClassDto>> getAllClasses() {
        try {
            return new ResponseEntity<>(classService.getAllClasses(), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/class/getClassByDate", method = RequestMethod.GET)
    public ResponseEntity<List<ClassDto>> getClassByDate() {
        try {
            return new ResponseEntity<>(classService.getClassByDate(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/class/deleteClass", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteClass(@RequestParam(name = "classId") Long classId) {

        try {
            classService.deleteApartament(classId);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
