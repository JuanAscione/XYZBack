package com.example.xyzhotelddd.domain.reservation;
import java.util.List;

public interface ReservationRepository {

    Reservation findById(Long reservationId);

    List<Reservation> findAll();

    void save(Reservation room);

    void update(Reservation room);

    void delete(Reservation room);

}
