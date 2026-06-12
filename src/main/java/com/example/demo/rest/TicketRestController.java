package com.example.demo.rest;

import com.example.demo.entities.Ticket;
import com.example.demo.entities.User;
import com.example.demo.services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping ("/api")
@RestController
public class TicketRestController {
    TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @Operation(description = "Получение текущего списка всех билетов")
    @ApiResponse(responseCode = "200", description = "Успешное получение всех билетов")
    @ApiResponse(responseCode = "404", description = "Ошибка получения билетов")
    @GetMapping ("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }
    @Operation(description = "Получение текущего списка всех билетов пользователя")
    @ApiResponse(responseCode = "200", description = "Успешное получение всех билетов пользователя")
    @ApiResponse(responseCode = "404", description = "Ошибка получения билетов пользователя")
    @GetMapping ("/user-tickets")
    public List<Ticket> getAllTicketsByUser() {
        return ticketService.getAllTicketsByUser();
    }
    @Operation(description = "Получение билета по его id")
    @ApiResponse(responseCode = "200", description = "Успешное получение билета по его id")
    @ApiResponse(responseCode = "404", description = "Ошибка получения билета по его id")
    @GetMapping ("/tickets/get/{id}")
    public Optional<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }
    @Operation(description = "Удаление билета по его id")
    @ApiResponse(responseCode = "200", description = "Успешное удаление билета по его id")
    @ApiResponse(responseCode = "404", description = "Ошибка удаления билета по его id")
    @DeleteMapping ("/tickets/delete/{id}")
    public void deleteTicketById(@PathVariable Long id) {
        ticketService.deleteTicketById(id);
    }
    @Operation(description = "Создание билета и его покупка")
    @ApiResponse(responseCode = "200", description = "Успешное создание и покупка билета")
    @ApiResponse(responseCode = "404", description = "Создать и купить билет не удалось")
    @PostMapping ("/tickets/add")
    public void addTicket(@RequestParam Long flightId, @RequestParam String firstName, @RequestParam String lastName, @RequestParam @Valid int age, @RequestParam @Valid String gender, @RequestParam String passportNumber) {
        ticketService.buyTicket(flightId, firstName, lastName, age, gender, passportNumber);
    }
    @Operation(description = "Удаление пассажира и возвращение его билета на рейс")
    @ApiResponse(responseCode = "200", description = "Успешное удаление пассажира и возращение его билета на рейс")
    @ApiResponse(responseCode = "404", description = "Удалить пассажира и вернуть его билет на рейс не удалось")
    @DeleteMapping ("/tickets/delete-passenger")
    public void deletePassengerAndReturnSeat(@RequestParam Long passengerId) {
        ticketService.deletePassengerAndReturnSeat(passengerId);
    }
}
