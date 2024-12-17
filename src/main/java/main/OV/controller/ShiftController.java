package main.OV.controller;

import jakarta.validation.ConstraintViolationException;
import main.OV.db.entity.ShiftEntity;
import main.OV.dto.ShiftDto;
import main.OV.service.IShiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftController {

    private IShiftService shiftService;

    @RequestMapping(value = "/shifts/saveShift", method = RequestMethod.POST)
    public ResponseEntity<?> saveShift(@RequestBody ShiftEntity shift) {
        try {

            ShiftEntity newShift = shiftService.saveShift(shift);

            return new ResponseEntity<>(newShift, HttpStatus.CREATED);

        } catch (ConstraintViolationException e) {
            String errors = e.getConstraintViolations().stream().map(s -> s.getMessage()).collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/shifts/editShift", method = RequestMethod.PATCH)
    public ResponseEntity<?> editShift(@RequestBody ShiftEntity shift) {
        try {

            ShiftEntity newShift = shiftService.editShift(shift);

            return new ResponseEntity<>(newShift, HttpStatus.CREATED);

        } catch (ConstraintViolationException e) {
            String errors = e.getConstraintViolations().stream().map(s -> s.getMessage()).collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/shifts/getAllShift", method = RequestMethod.GET)
    public ResponseEntity<List<ShiftDto>> getAllShift() {
        try {
            return new ResponseEntity<>(shiftService.getAllShift(), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/shifts/getShiftByDate", method = RequestMethod.GET)
    public ResponseEntity<List<ShiftDto>> getShiftByDate() {
        try {
            return new ResponseEntity<>(shiftService.getShiftByDate(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
