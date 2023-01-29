package com.bridgelabz.BookStoreApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BookStoreAppApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to Book Store Application");
		SpringApplication.run(BookStoreAppApplication.class, args);
		log.info("Logging started.............");
		System.out.println("Started......");
	}
}
