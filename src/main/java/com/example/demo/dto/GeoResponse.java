package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoResponse {
    private double lat;
    private double lon;

    public GeoResponse(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
