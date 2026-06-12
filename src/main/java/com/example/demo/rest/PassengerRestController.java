package com.example.demo.rest;

import com.example.demo.entities.Passenger;
import com.example.demo.services.PassengerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping ("/api")
@RestController
public class PassengerRestController {
    PassengerService passengerService;

    public PassengerRestController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }
    @Operation(description = "Получение текущего списка всех пассажиров")
    @ApiResponse(responseCode = "200", description = "Успешное получение всех пассажиров")
    @ApiResponse(responseCode = "404", description = "Ошибка получение пассажиров")
    @GetMapping ("/passengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }
    @Operation(description = "Удаление пассажира по его id")
    @ApiResponse(responseCode = "200", description = "Успешное удаление пассажира по id")
    @ApiResponse(responseCode = "404", description = "Удалить пассажира по id не получилось")
    @DeleteMapping ("/passengers/delete/{id}")
    public void deletePassengerById(@PathVariable Long id) {
        passengerService.deletePassengerById(id);
    }
    @Operation(description = "Добавление нового пассажира в базу пассажиров")
    @ApiResponse(responseCode = "200", description = "Успешное добавление пассажира в базу")
    @ApiResponse(responseCode = "404", description = "Добавить пассажира в базу не удалось")
    @PostMapping ("/passengers/add")
    public void addPassenger(@RequestParam String firstName, @RequestParam String lastName, @RequestParam @Valid int age, @RequestParam @Valid String gender, @RequestParam String passportNumber) {
        passengerService.createPassenger(firstName, lastName, age, gender, passportNumber);
    }
    @Operation(description = "Получение пассажира по его id")
    @ApiResponse(responseCode = "200", description = "Успешное получение пассажира по id")
    @ApiResponse(responseCode = "404", description = "Ошибка получения пассажира по его id")
    @GetMapping ("/passengers/get/{id}")
    public Optional<Passenger> getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }
}
