package main.OV.controller;

import jakarta.validation.ConstraintViolationException;
import main.OV.config.LoginRequest;
import main.OV.db.entity.ClientEntity;
import main.OV.db.entity.EmployeeEntity;
import main.OV.dto.EmployeeDto;
import main.OV.service.IClientService;
import main.OV.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IClientService clientService;



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
            // Buscar primero si es un empleado
            EmployeeEntity employee = employeeService.findByEmail(loginRequest.getEmail());
            if (employee != null && employee.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(Map.of(
                        "message", "Login exitoso",
                        "type", "employee"
                ));
            }

            // Buscar si es un cliente
            ClientEntity client = clientService.findByEmail(loginRequest.getEmail());
            if (client != null && client.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(Map.of(
                        "message", "Login exitoso",
                        "type", "client"
                ));
            }

            // Si no coincide ninguno
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "message", "Correo o contraseña incorrecta. Intentelo de nuevo"
            ));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
