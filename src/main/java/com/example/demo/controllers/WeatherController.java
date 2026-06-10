package com.example.demo.controllers;

import com.example.demo.dto.GeoResponse;
import com.example.demo.services.GeoCodingService;
import com.example.demo.services.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {
    WeatherService weatherService;
    GeoCodingService geoCodingService;

    public WeatherController(WeatherService weatherService, GeoCodingService geoCodingService) {
        this.weatherService = weatherService;
        this.geoCodingService = geoCodingService;
    }

    @GetMapping("/weather-api")
    public String getForm() {
        return "apis/weather-api";
    }
    @PostMapping("/weather-api")
    public String getAirTemperature(@RequestParam String city, Model model) {
        GeoResponse response = geoCodingService.getCoordinates(city);
        model.addAttribute("temperature", weatherService.getCurrentTemperature(response.getLat(), response.getLon()));
        return "apis/weather-api";
    }
}
