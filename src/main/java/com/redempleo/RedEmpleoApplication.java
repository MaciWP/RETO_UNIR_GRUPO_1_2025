package com.redempleo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for RedEmpleo system.
 * Serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication
public class RedEmpleoApplication {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RedEmpleoApplication.class, args);
    }
}