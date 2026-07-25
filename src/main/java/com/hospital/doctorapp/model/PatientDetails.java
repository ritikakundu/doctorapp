package com.hospital.doctorapp.model;

import lombok.Data;

@Data
public class PatientDetails {
    private String name;

    private String email;

    private String phone;

    private int age;

    private String password;
}
