package com.example.demo.services;

import com.example.demo.entities.Flight;
import com.example.demo.entities.Passenger;
import com.example.demo.entities.Ticket;
import com.example.demo.entities.User;
import com.example.demo.repositories.FlightRepository;
import com.example.demo.repositories.PassengerRepository;
import com.example.demo.repositories.TicketRepository;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(value = MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    PassportValidationService passportService;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private PassengerRepository passengerRepository;
    @Mock
    private FlightRepository flightRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private TicketService ticketService;

    @Test
    void testTicketService_WrongPassport() {
        Mockito.when(passportService.validate(anyString(), anyString(), anyString())).thenReturn(false);
        assertThrows(RuntimeException.class, () ->  {
            ticketService.buyTicket(1L, "Danya", "Kirillov", 25, "M", "1234 567890");
        });
    }

    @Test
    void testTicketService_SuccessfulBuyTicket() {
        try (var mockedSecurity = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext context = Mockito.mock(SecurityContext.class);
            Authentication auth = Mockito.mock(Authentication.class);
            Mockito.when(SecurityContextHolder.getContext()).thenReturn(context);
            Mockito.when(context.getAuthentication()).thenReturn(auth);
            Mockito.when(auth.getName()).thenReturn("testUser");
            Mockito.when(passportService.validate(anyString(), anyString(), anyString())).thenReturn(true);
            User user = new User();
            Mockito.when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
            Flight flight = new Flight();
            flight.setAvailableSeats(100);
            flight.setTotalSeats(100);
            Mockito.when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
            Mockito.when(ticketRepository.findOccupiedSeatsByFlight(flight)).thenReturn(List.of());

            assertDoesNotThrow(() -> {
                ticketService.buyTicket(1L, "Danya", "Kirillov", 25, "M", "1234 567890");
            });
            Mockito.verify(ticketRepository).save(any(Ticket.class));
            Mockito.verify(passengerRepository).save(any(Passenger.class));
            Assertions.assertEquals(99, flight.getAvailableSeats());
        }
    }
}
