package main.OV.controller;

import jakarta.validation.ConstraintViolationException;
import main.OV.db.entity.RegisterEntity;
import main.OV.dto.RegisterDto;
import main.OV.service.IRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

public class RegisterController {

    private IRegisterService registerService;

    @RequestMapping(value = "/register/saveRegister", method = RequestMethod.POST)
    public ResponseEntity<?> saveRegister(@RequestBody RegisterEntity register) {
        try {
            RegisterEntity newRegister = registerService.saveRegister(register);

            return new ResponseEntity<>(newRegister, HttpStatus.CREATED);

        } catch (ConstraintViolationException e) {
            String errors = e.getConstraintViolations().stream().map(s -> s.getMessage()).collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/register/getRegisters", method = RequestMethod.GET)
    public ResponseEntity<List<RegisterDto>> getAllWeekRegisters() {
        try {
            return new ResponseEntity<>(registerService.getAllWeekRegisters(), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
