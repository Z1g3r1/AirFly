package com.example.demo.services;

import org.springframework.stereotype.Service;

@Service
public class PassportValidationService {
    public boolean validate(String firstName, String lastName, String passportNumber) {
        if ((!firstName.isEmpty() && !lastName.isEmpty()) && passportNumber.matches("\\d{4} \\d{6}")) {
            return true;
        }
        return false;
    }
}
