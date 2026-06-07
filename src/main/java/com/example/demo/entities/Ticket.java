package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity @Table (name = "tickets")
public class Ticket {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    @JoinColumn
    @ManyToOne
    Flight flight;
    @OneToOne
    Passenger passenger;
    @ManyToOne
    User user;
    private int seatNumber;
    private double price;
    private LocalDate purchaseDate;
    public Ticket() {}
    public Ticket(Flight flight, Passenger passenger, User user, int seatNumber, double price, LocalDate purchaseDate) {
        this.flight = flight;
        this.passenger = passenger;
        this.user = user;
        this.seatNumber = seatNumber;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

//    public Ticket(Flight flight, Passenger passenger, User user, LocalDate purchaseDate) {
//        this.flight = flight;
//        this.passenger = passenger;
//        this.user = user;
//        this.purchaseDate = purchaseDate;
//    }
}
