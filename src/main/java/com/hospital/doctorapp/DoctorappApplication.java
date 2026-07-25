package com.hospital.doctorapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class DoctorappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorappApplication.class, args);
	}

}
