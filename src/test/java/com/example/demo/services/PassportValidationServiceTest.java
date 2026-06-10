package com.example.demo.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class PassportValidationServiceTest {
    PassportValidationService service = new PassportValidationService();
    
    @Test
    public void testValidate_EmptyFirstName_ReturnsFalse() {
        boolean test = service.validate("", "Kirillov", "1234 421123");
        assertFalse(test);
    }

    @Test
    void testValidate_EmptyLastName_ReturnFalse() {
        boolean test = service.validate("Danya", "", "1234 123112");
        assertFalse(test);
    }

    @Test
    void testValidate_WrongPassportNonSpace_ReturnFalse() {
        boolean test = service.validate("Danya", "Kirillov", "1234123123");
        assertFalse(test);
    }

    @Test
    void testValidate_WrongPassportNonNumbers_ReturnFalse() {
        boolean test = service.validate("Danya", "Kirillov", "abcd efghij");
        assertFalse(test);
    }

    @Test
    void testValidate_WrongPassportNonTenNumbers_ReturnFalse() {
        boolean test = service.validate("Danya", "Kirillov", "1234 5678901");
        assertFalse(test);
    }
}
