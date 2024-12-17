package main.OV.service;

import main.OV.db.entity.EmployeeEntity;
import main.OV.dto.EmployeeDto;

import java.util.List;


public interface IEmployeeService {
    List<EmployeeDto> getEmployeeById();

    EmployeeEntity editEmployee(EmployeeEntity employee);


    EmployeeEntity saveEmployee(String email, String password);

    List<EmployeeDto> getAllEmployee();

    EmployeeEntity findByEmail(String email);
}
