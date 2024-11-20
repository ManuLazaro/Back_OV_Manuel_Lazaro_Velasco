package main.OV.dto;

import lombok.Data;

@Data
public class CenterDto {
    private String name;
    private String address;
    private String phone;
    private String email;

    public CenterDto() {
    }

    public void CenterDTO(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
