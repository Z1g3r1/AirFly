package com.example.demo.controllers;

import com.example.demo.services.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightController {
    FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping ("/flights/page")
    public String getAllFlights(Model model, @RequestParam(defaultValue = "false") boolean admin) {
        model.addAttribute("flights", flightService.getAllFlights());
        model.addAttribute("admin", admin);
        return "flights";
    }
}
