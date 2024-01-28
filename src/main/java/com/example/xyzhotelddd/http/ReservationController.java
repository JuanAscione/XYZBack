package com.example.xyzhotelddd.http;

import com.example.xyzhotelddd.domain.account.Client;
import com.example.xyzhotelddd.domain.amenities.Room;
import com.example.xyzhotelddd.domain.amenities.RoomType;
import com.example.xyzhotelddd.domain.reservation.Reservation;
import com.example.xyzhotelddd.domain.reservation.ReservationService;
import com.example.xyzhotelddd.http.dto.ClientResponseDTO;
import com.example.xyzhotelddd.http.dto.DepositResponse;
import com.example.xyzhotelddd.http.dto.ReservationRequestDTO;
import com.example.xyzhotelddd.http.dto.ReservationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController (ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/makeReservation")
    public ResponseEntity<DepositResponse> makeReservation(@RequestBody ReservationRequestDTO reservationDTO) {
        try {
            reservationService.createReservation(reservationDTO);
            DepositResponse response = new DepositResponse("Reservation created successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            DepositResponse response = new DepositResponse(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ReservationResponse>> getAllReservationsByClientId(@PathVariable Long clientId) {
        try {
            List<Reservation> reservations = reservationService.getAllReservationsByClientId(clientId);
            List<ReservationResponse> response = new ArrayList<>();

            for (Reservation reservation : reservations) {
                ReservationResponse reservationResponse = new ReservationResponse();
                reservationResponse.setId(reservation.getId());
                reservationResponse.setCheckInDate(reservation.getCheckInDate().toString());
                reservationResponse.setNumberOfNights(reservation.getNumberOfNights());
                reservationResponse.setStatus(reservation.getStatus().name());

                // Creating ClientResponse
                ReservationResponse.ClientResponse clientResponse = new ReservationResponse.ClientResponse();
                Client client = reservation.getClient();
                clientResponse.setId(Long.valueOf(client.getId()));
                clientResponse.setEmail(client.getEmail());
                reservationResponse.setClient(clientResponse);

                // Creating RoomResponseDTO list
                List<ReservationResponse.RoomResponseDTO> roomResponseDTOList = new ArrayList<>();
                for (Room room : reservation.getRooms()) {
                    ReservationResponse.RoomResponseDTO roomResponseDTO = new ReservationResponse.RoomResponseDTO();
                    roomResponseDTO.setType(room.getType().name());
                    roomResponseDTO.setPricePerNight(room.getPricePerNight());
                    roomResponseDTOList.add(roomResponseDTO);
                }
                reservationResponse.setRooms(roomResponseDTOList);

                response.add(reservationResponse);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> deleteReservationById(@PathVariable Long reservationId) {
        try {
            reservationService.deleteReservationById(reservationId);
            return ResponseEntity.ok().body(Map.of("message", "Reservation deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{reservationId}/confirm-payment")
    public ResponseEntity<?> confirmPayment(@PathVariable Long reservationId) {
        try {
            reservationService.confirmPayment(reservationId);
            return ResponseEntity.ok().body(Map.of("message", "Payment confirmed successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }

}

