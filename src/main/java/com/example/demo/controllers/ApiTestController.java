package com.example.demo.controllers;

import com.example.demo.services.AgifyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApiTestController {
    AgifyService agifyService;

    public ApiTestController(AgifyService agifyService) {
        this.agifyService = agifyService;
    }

    @GetMapping ("/api-test")
    public String showForm() {
        return "apis/api-test";
    }
    @PostMapping ("/api-test")
    public String getPrediction(@RequestParam String name, Model model) {
        int json = agifyService.getAge(name);
        model.addAttribute("result", json);
        return "apis/api-test";
    }
}
