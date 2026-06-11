package com.example.demo.services;

import com.example.demo.dto.GeoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class GeoCodingService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    public GeoResponse getCoordinates(String city) {
        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=ru&format=json";
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "AirCompanyProject/1.0 timofeyvoronin600@gmail.com");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, String.class
        );
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode location = root.path("results").get(0);
            double lat = location.path("latitude").asDouble();
            double lon = location.path("longitude").asDouble();
            return new GeoResponse(lat, lon);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to get coordinates for city: " + city, e);
        }
    }
}
