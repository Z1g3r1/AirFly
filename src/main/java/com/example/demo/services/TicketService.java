package com.example.demo.services;

import com.example.demo.entities.Flight;
import com.example.demo.entities.Passenger;
import com.example.demo.entities.Ticket;
import com.example.demo.entities.User;
import com.example.demo.repositories.FlightRepository;
import com.example.demo.repositories.PassengerRepository;
import com.example.demo.repositories.TicketRepository;
import com.example.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class TicketService {
    TicketRepository ticketRepository;
    PassengerRepository passengerRepository;
    FlightRepository flightRepository;
    UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, PassengerRepository passengerRepository, FlightRepository flightRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    public void buyTicket(Long flightId, String firstName, String lastName, int age, String gender, String passportNumber) {
        Random random = new Random();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        Passenger passenger = new Passenger(firstName, lastName, age, gender, passportNumber);
        Ticket ticket = new Ticket(flight, passenger, user, random.nextInt(flight.getAvailableSeats()), flight.getPrice(), LocalDate.now());
        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        passengerRepository.save(passenger);
        ticketRepository.save(ticket);
    }
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    public List<Ticket> getAllTicketsByUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
        return ticketRepository.findByUser(user);
    }
    @Transactional
    public void deletePassengerAndReturnSeat(Long passengerId) {
        Ticket ticket = ticketRepository.findByPassengerId(passengerId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        Flight flight = ticket.getFlight();
        flight.setAvailableSeats(flight.getAvailableSeats()+1);
        ticketRepository.delete(ticket);
        passengerRepository.delete(passengerRepository.findById(passengerId).orElseThrow(() -> new RuntimeException("Passenger not found")));
    }
}
