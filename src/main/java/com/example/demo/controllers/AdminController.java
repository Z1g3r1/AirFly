package com.example.demo.controllers;

import com.example.demo.services.FlightService;
import com.example.demo.services.PassengerService;
import com.example.demo.services.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.time.LocalDate;

@Controller
@RequestMapping ("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    FlightService flightService;
    TicketService ticketService;
    PassengerService passengerService;

    public AdminController(FlightService flightService, TicketService ticketService, PassengerService passengerService) {
        this.flightService = flightService;
        this.ticketService = ticketService;
        this.passengerService = passengerService;
    }

    @GetMapping("/flights/add")
    public String addFlightForm(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "flights/add-flight";
    }
    @PostMapping("/flights/add")
    public String createFlight(@RequestParam String flightNumber, @RequestParam String companyName, @RequestParam String departureCity, @RequestParam String arrivalCity, @RequestParam LocalDate departureTime, @RequestParam LocalDate arrivalTime, @RequestParam double price, @RequestParam int availableSeats) {
        flightService.addFlight(flightNumber, companyName, departureCity, arrivalCity, departureTime, arrivalTime, price, availableSeats);
        return "redirect:/flights/page";
    }
    @GetMapping ("/panel")
    public String getAdminPanel(Model model) {
        model.addAttribute("addFlightUrl", "/admin/flights/add");
        model.addAttribute("manageFlightsUrl", "/flights/page");
        return "admin/admin-panel";
    }
    @PostMapping ("/flights/delete")
    public String deleteFlight(@RequestParam Long id) {
        flightService.deleteFlightById(id);
        return "redirect:/flights/page";
    }
    @GetMapping ("/tickets")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "admin/tickets";
    }
    @GetMapping ("/passengers")
    public String getAllPassengers(Model model) {
        model.addAttribute("passengers", passengerService.getAllPassengers());
        return "admin/passengers";
    }
    @PostMapping("/passengers/delete-passenger")
    public String deletePassenger(@RequestParam Long passengerId) {
        ticketService.deletePassengerAndReturnSeat(passengerId);
        return "redirect:/admin/passengers";
    }
}
