package com.example.demo;

import com.example.demo.entities.Flight;
import com.example.demo.entities.User;
import com.example.demo.repositories.FlightRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(FlightRepository flightRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				User admin = new User("admin", passwordEncoder.encode("admin12345"), "ROLE_ADMIN");
				userRepository.save(admin);
			}
			if (flightRepository.count() == 0) {
				Flight flight1 = new Flight(
						"SU-123", "Aeroflot", "Москва", "Санкт-Петербург",
						LocalDate.of(2026, 6, 15), LocalDate.of(2026, 6, 15),
						5000.0, 100
				);
				Flight flight2 = new Flight(
						"BA-456", "S7 Airlines", "Москва", "Новосибирск",
						LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 1),
						7500.0, 50
				);
				Flight flight3 = new Flight(
						"LH-789", "Lufthansa", "Москва", "Франкфурт",
						LocalDate.of(2026, 8, 20), LocalDate.of(2026, 8, 20),
						12000.0, 200
				);
				flightRepository.save(flight1);
				flightRepository.save(flight2);
				flightRepository.save(flight3);
			}
		};
	}
}
