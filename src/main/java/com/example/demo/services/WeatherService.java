package com.example.demo.services;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String getWeather(double lat, double lon) {
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=" + lat + "&lon=" + lon;
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "AirCompanyProject/1.0 timofeyvoronin600@gmail.com");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, String.class
        );
        return response.getBody();
    }
    public double getCurrentTemperature(double lat, double lon) {
        String json = getWeather(lat, lon);
        ObjectMapper mapper = new ObjectMapper(); // инструмент для работы с JSON
        JsonNode root = mapper.readTree(json); // Создание системы деревьев с корневым узлом
        JsonNode details = root.path("properties").path("timeseries").get(0)
                .path("data").path("instant").path("details");
        return details.path("air_temperature").asDouble();
    }
}
