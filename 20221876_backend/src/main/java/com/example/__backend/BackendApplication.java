package com.example.__backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring Boot application.
 * This class contains the main method that starts the application.
 */
@SpringBootApplication // Indicates this is a Spring Boot application
public class BackendApplication {

	public static void main(String[] args) {
		// Launch the Spring Boot application
		SpringApplication.run(BackendApplication.class, args);
	}

}