package com.example.xyzhotelddd.http.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequestDto requestDto) {
        // Parse requestDto and invoke corresponding Application Service method
        reservationService.createReservation(requestDto);
        return ResponseEntity.ok("Reservation created successfully.");
    }

    // Other controller methods for handling different HTTP requests
}

