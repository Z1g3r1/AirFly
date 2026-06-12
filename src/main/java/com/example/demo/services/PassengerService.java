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
    public void deletePassengerById(Long id) {
        passengerRepository.deleteById(id);
    }
    public void createPassenger(String firstName, String lastName, int age, String gender, String passportNumber) {
        Passenger passenger = new Passenger(firstName, lastName, age, gender, passportNumber);
        passengerRepository.save(passenger);
    }
    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }
}
