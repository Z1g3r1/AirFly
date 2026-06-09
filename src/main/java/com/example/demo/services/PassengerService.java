package com.example.demo.services;

import com.example.demo.entities.Passenger;
import com.example.demo.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }
}
