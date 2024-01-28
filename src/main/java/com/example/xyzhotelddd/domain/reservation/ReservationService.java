package com.example.xyzhotelddd.domain.reservation;

import com.example.xyzhotelddd.database.ClientRepository;
import com.example.xyzhotelddd.database.ReservationRepository;
import com.example.xyzhotelddd.database.WalletRepository;
import com.example.xyzhotelddd.domain.account.Client;
import com.example.xyzhotelddd.domain.amenities.Room;
import com.example.xyzhotelddd.domain.amenities.RoomType;
import com.example.xyzhotelddd.domain.payment.WalletService;
import com.example.xyzhotelddd.domain.reservation.Reservation;
import com.example.xyzhotelddd.domain.reservation.ReservationStatus;
import com.example.xyzhotelddd.http.dto.ReservationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final WalletService walletService;
    private final ClientRepository clientRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, WalletRepository walletRepository, WalletService walletService, ClientRepository clientRepository) {
        this.reservationRepository = reservationRepository;
        this.walletService = walletService;
        this.clientRepository = clientRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getAllReservationsByClientId(Long clientId) {
        return reservationRepository.findByClientId(clientId);
    }


    public void deleteReservationById(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    public Reservation createReservation(ReservationRequestDTO reservationDTO) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        String dateInString = reservationDTO.getCheckInDate();
        Date date = formatter.parse(dateInString);

        Integer totalPricePerNight = 0;

        Client client = clientRepository.findByEmail(reservationDTO.getClientEmail());

        if(client == null){
            throw new Exception("Client not found");
        }

        List<Room> rooms = new ArrayList<>();

        for (ReservationRequestDTO.RoomInfos roomInfo : reservationDTO.getRoomInfos()) {
            for (int i = 0; i < roomInfo.getNumberOfRooms(); i++) {
                Room roomReserved = new Room(RoomType.fromString(roomInfo.getRoomType()));
                totalPricePerNight += roomReserved.getPricePerNight();
                rooms.add(roomReserved);
            }
        }

        Integer totalPrice = totalPricePerNight * reservationDTO.getNumberOfNights();

        try {
            walletService.payForReservation(client, BigDecimal.valueOf(totalPrice), "EUR");
        } catch (Exception e) {
            throw new IllegalStateException("Insufficient funds");
        }

        Reservation newReservation = new Reservation(
                client,
                rooms,
                date,
                reservationDTO.getNumberOfNights(),
                totalPrice,
                ReservationStatus.PENDING
        );
        return reservationRepository.save(newReservation);
    }


    public void confirmPayment(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        try {
            walletService.payForReservation(reservation.getClient(), BigDecimal.valueOf(reservation.getTotalPrice()), "EUR");
            reservation.setStatus(ReservationStatus.APPROVED);
        }catch (Exception e){
            throw new IllegalStateException("Insufficient funds");
        }
        reservationRepository.save(reservation);
    }
}
