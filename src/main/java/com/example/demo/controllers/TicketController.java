package com.example.demo.controllers;

import com.example.demo.entities.Flight;
import com.example.demo.repositories.FlightRepository;
import com.example.demo.services.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {
    TicketService ticketService;
    FlightRepository flightRepository;

    public TicketController(TicketService ticketService, FlightRepository flightRepository) {
        this.ticketService = ticketService;
        this.flightRepository = flightRepository;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tickets/my")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTicketsByUser());
        return "tickets";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/flights/{flightId}/purchase")
    public String getPurchaseForm(@PathVariable Long flightId, Model model) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        model.addAttribute("flight", flight);
        return "buy-ticket";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping ("/flights/{flightId}/buy_ticket")
    public String buyTicket(@PathVariable Long flightId, @RequestParam String firstName, @RequestParam String lastName, @RequestParam int age, @RequestParam String gender, @RequestParam String passportNumber) {
        ticketService.buyTicket(flightId, firstName, lastName, age, gender, passportNumber);
        return "redirect:/tickets/my";
    }
}
