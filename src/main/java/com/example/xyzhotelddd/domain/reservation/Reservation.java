package com.example.xyzhotelddd.domain.reservation;

import com.example.xyzhotelddd.domain.account.Client;
import com.example.xyzhotelddd.domain.amenities.Room;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_email")
    private Client client;

    @ElementCollection
    @CollectionTable(name = "reservation_rooms", joinColumns = @JoinColumn(name = "reservation_id"))
    private List<Room> rooms;

    private Date checkInDate;

    private Integer numberOfNights;

    private Integer totalPrice;

    private ReservationStatus status;

    public Reservation(Client client, List<Room> rooms, Date checkInDate, Integer numberOfNights, Integer totalPrice, ReservationStatus status) {
        this.client = client;
        this.rooms = rooms;
        this.checkInDate = checkInDate;
        this.totalPrice = totalPrice;
        this.numberOfNights = numberOfNights;
        this.status = status;
    }

    public Reservation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
