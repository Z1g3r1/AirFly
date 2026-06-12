package com.example.demo.services;

import com.example.demo.entities.Passenger;
import com.example.demo.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }
    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }
}
