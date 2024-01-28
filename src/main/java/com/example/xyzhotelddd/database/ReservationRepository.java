package com.example.xyzhotelddd.database;
import com.example.xyzhotelddd.domain.account.Client;
import com.example.xyzhotelddd.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByClientId(Long clientId);

}
