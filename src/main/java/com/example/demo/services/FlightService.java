package com.example.demo.services;

import com.example.demo.entities.Flight;
import com.example.demo.repositories.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    public Flight addFlight(String flightNumber, String companyName, String departureCity, String arrivalCity, LocalDate departureTime, LocalDate arrivalTime, double price, int availableSeats) {
        Flight flight = new Flight(flightNumber, companyName, departureCity, arrivalCity, departureTime, arrivalTime, price, availableSeats);
        return flightRepository.save(flight);
    }
    public void deleteFlightById(Long id) {
        flightRepository.deleteById(id);
    }
}
