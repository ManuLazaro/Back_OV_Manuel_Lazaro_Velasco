package main.OV.controller;

import jakarta.validation.ConstraintViolationException;
import main.OV.config.LoginRequest;
import main.OV.db.entity.EmployeeEntity;
import main.OV.dto.EmployeeDto;
import main.OV.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;



    @PostMapping("/saveEmployee")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeEntity employee){
        try {
            EmployeeEntity newEmployee = employeeService.saveEmployee(employee.getEmail(), employee.getPassword());
            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            String errors = e.getConstraintViolations().stream().map(s -> s.getMessage()).collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/editEmployee")
    public ResponseEntity<?> editEmployee(@RequestBody EmployeeEntity employee) {
        try {

            EmployeeEntity updatedEmployee  = employeeService.editEmployee(employee);

            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

        } catch (ConstraintViolationException e) {
            String errors = e.getConstraintViolations().stream().map(s -> s.getMessage()).collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmployeeById")
    public ResponseEntity<List<EmployeeDto>> getEmployeeById() {
        try {
            return new ResponseEntity<>(employeeService.getEmployeeById(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        try {
            List<EmployeeDto> employees = employeeService.getAllEmployee();

            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Verificar credenciales
            EmployeeEntity employee = employeeService.findByEmail(loginRequest.getEmail());

            if (employee != null && employee.getPassword().equals(loginRequest.getPassword())) {
                // Si las credenciales son correctas, puedes generar un token o simplemente devolver un éxito.
                return ResponseEntity.ok("Login exitoso");
            } else {
                return ResponseEntity.ok("Correo o contraseña incorrecta. Intentelo de nuevo ");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
