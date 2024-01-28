package com.example.xyzhotelddd.http.dto;

import lombok.Data;

import java.util.List;

public class ReservationRequestDTO {

    private Long id;

    private String clientEmail;
    private List<RoomInfos> roomInfos;
    private String checkInDate;
    private int numberOfNights;

    @Data
    public static class RoomInfos {
        private String roomType;
        private int numberOfRooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public List<RoomInfos> getRoomInfos() {
        return roomInfos;
    }

    public void setRoomInfos(List<RoomInfos> roomInfos) {
        this.roomInfos = roomInfos;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }
}
