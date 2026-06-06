package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
@Entity @Table (name = "passengers")
public class Passenger {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Min(0) @Max(125)
    private int age;
    @Pattern(regexp = "[MF]")
    private String gender;
    private String passportNumber;
    public Passenger() {}
    public Passenger(String firstName, String lastName, int age, String gender, String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.passportNumber = passportNumber;
    }
}
