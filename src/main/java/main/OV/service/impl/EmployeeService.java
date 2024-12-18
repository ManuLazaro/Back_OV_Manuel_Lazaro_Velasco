package main.OV.service.impl;

import main.OV.db.entity.EmployeeEntity;
import main.OV.db.repository.EmployeeRepository;
import main.OV.dto.EmployeeDto;
import main.OV.service.IEmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    // Inyección de la dependencia a través del constructor
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDto> getEmployeeById() {
        return null;
    }

    @Override
    public EmployeeEntity editEmployee(EmployeeEntity employee) {
        return null;
    }

    @Override
    public EmployeeEntity saveEmployee(String email, String password) {
        // Crear un nuevo EmployeeEntity con los datos recibidos
        EmployeeEntity newEmployee = new EmployeeEntity();
        newEmployee.setEmail(email);
        newEmployee.setPassword(password);
        // Asignar valores por defecto para otros campos
        newEmployee.setFirstName("Nombre por defecto");
        newEmployee.setLastName("Apellido por defecto");
        newEmployee.setStatus("activo");
        newEmployee.setHireDate(LocalDate.now());
        newEmployee.setCreatedAt(LocalDateTime.now());
        newEmployee.setUpdatedAt(LocalDateTime.now());
        newEmployee.setClassEntity(null);
        newEmployee.setTurno("tarde");


        // Guardar el empleado en la base de datos
        return employeeRepository.save(newEmployee);
    }

    /**
     * @return todos los datos de los empleados
     */
    @Override
    public List<EmployeeDto> getAllEmployee() {
        // Obtiene los empleados con rol "monitor"
        List<EmployeeEntity> employees = employeeRepository.findAll();

        return employees.stream().map(employee -> {
            EmployeeDto employeeDto = new EmployeeDto();

            employeeDto.setName(employee.getFirstName());
            employeeDto.setLastName(employee.getLastName());
            employeeDto.setPhone(employee.getPhone());
            employeeDto.setRole(employee.getRole());
            employeeDto.setStatus(employee.getStatus());
            employeeDto.setTurno(employee.getTurno());

            // Mapea la clase si existe
            if (employee.getClassEntity() != null) {
                employeeDto.setClassName(employee.getClassEntity().getName());  // Asigna solo el nombre de la clase
            }

            // Mapea la fecha de creación (created_at) como llegada
            if (employee.getCreatedAt() != null) {
                employeeDto.setLlegada(employee.getCreatedAt());
            }

            return employeeDto;
        }).collect(Collectors.toList());
    }

    /**
     * @param email
     * @return
     */
    @Override
    public EmployeeEntity findByEmail(String email) {
        return employeeRepository.findByEmail(email).orElse(null); // Devolver null si no existe
    }



}
