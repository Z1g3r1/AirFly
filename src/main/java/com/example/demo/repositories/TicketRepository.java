package com.example.demo.repositories;

import com.example.demo.entities.Flight;
import com.example.demo.entities.Ticket;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);
    Optional<Ticket> findByPassengerId(Long id);
    @Query("SELECT t.seatNumber FROM Ticket t WHERE t.flight = :flight")
    List<Integer> findOccupiedSeatsByFlight(@Param("flight") Flight flight);
}
