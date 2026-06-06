package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity @Table (name = "flights")
public class Flight {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    private String companyName;
    private String departureCity;
    private String arrivalCity;
    private LocalDate departureTime;
    private LocalDate arrivalTime;
    private double price;
    private int availableSeats;
    public Flight() {}

    public Flight(String flightNumber, String companyName, String departureCity, String arrivalCity, LocalDate departureTime, LocalDate arrivalTime, double price, int availableSeats) {
        this.flightNumber = flightNumber;
        this.companyName = companyName;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.availableSeats = availableSeats;
    }

}
