package main.OV.dto;

import java.time.LocalDateTime;

public class EmployeeDto {

    private String name;
    private String lastName;
    private String phone;

    private String role;

    private String status;
    private String turno;
    private String className;

    private LocalDateTime llegada;


    public EmployeeDto() {
    }

    public EmployeeDto(String name, String lastName, String phone, String role, String status, String turno, String className, LocalDateTime llegada) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
        this.status = status;
        this.turno = turno;
        this.className = className;
        this.llegada = llegada;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalDateTime getLlegada() {
        return llegada;
    }

    public void setLlegada(LocalDateTime llegada) {
        this.llegada = llegada;
    }
}
