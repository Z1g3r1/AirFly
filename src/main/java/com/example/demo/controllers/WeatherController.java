package com.example.demo.controllers;

import com.example.demo.services.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {
    WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather-api")
    public String getForm() {
        return "apis/weather-api";
    }
    @PostMapping("/weather-api")
    public String getAirTemperature(@RequestParam double lat, @RequestParam double lon, Model model) {
        model.addAttribute("temperature", weatherService.getCurrentTemperature(lat, lon));
        return "apis/weather-api";
    }
}
