package main.OV.service.impl;

import main.OV.db.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final IEmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<String> getEmployeeByEmail(String email) {
        return employeeRepository.findPasswordByEmail(email);
    }
}
