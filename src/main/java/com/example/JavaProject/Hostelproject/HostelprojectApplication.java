package com.example.JavaProject.Hostelproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HostelprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostelprojectApplication.class, args);
	}

}
