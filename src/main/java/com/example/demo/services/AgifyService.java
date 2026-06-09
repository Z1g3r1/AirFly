package com.example.demo.services;

import com.example.demo.dto.AgifyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AgifyService {
    RestTemplate restTemplate = new RestTemplate();
    public int getAge(String name) {
        String url = "https://api.agify.io/?name=" + name;
        AgifyResponse agifyResponse = restTemplate.getForObject(url, AgifyResponse.class);
        return agifyResponse.getAge();
    }
}
