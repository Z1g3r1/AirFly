package com.example.demo.rest;

import com.example.demo.entities.Flight;
import com.example.demo.services.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FlightRestController {
    FlightService flightService;

    public FlightRestController(FlightService flightService) {
        this.flightService = flightService;
    }
    @Operation (description = "Получение текущего списка всех рейсов")
    @ApiResponse(responseCode = "200", description = "Успешное получение всех рейсов")
    @ApiResponse(responseCode = "404", description = "Ошибка получение рейсов")
    @GetMapping ("/flights")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }
    @Operation(description = "Удаление рейса по его id")
    @ApiResponse(responseCode = "200", description = "Успешное удаление рейса из списка рейсов")
    @ApiResponse(responseCode = "404", description = "Удаление по id рейса не сработало")
    @DeleteMapping ("/flights/{id}/delete")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlightById(id);
    }
    @Operation(description = "Добавление рейса")
    @ApiResponse(responseCode = "200", description = "Успешное добавление рейса в список рейсов")
    @ApiResponse(responseCode = "404", description = "Добавить новый рейс в список рейсов не удалось")
    @PostMapping ("/flights/add")
    public Flight createFlight(@RequestParam String flightNumber, @RequestParam String companyName, @RequestParam String departureCity, @RequestParam String arrivalCity, @RequestParam LocalDate departureTime, @RequestParam LocalDate arrivalTime, @RequestParam double price, @RequestParam int availableSeats) {
        return flightService.addFlight(flightNumber, companyName, departureCity, arrivalCity, departureTime, arrivalTime, price, availableSeats);
    }
    @Operation(description = "Получение рейса по его id")
    @ApiResponse(responseCode = "200", description = "Успешное получение рейса по его id")
    @ApiResponse(responseCode = "404", description = "Получение рейса по id потерпело не удачу")
    @GetMapping("/flights/get/{id}")
    public Optional<Flight> getFlightById(@PathVariable Long id) {
        return flightService.getById(id);
    }
}
