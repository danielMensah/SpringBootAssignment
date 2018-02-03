package com.springassignment.demo;

import com.springassignment.demo.Database.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	EmployeeRespository employeeRespository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
