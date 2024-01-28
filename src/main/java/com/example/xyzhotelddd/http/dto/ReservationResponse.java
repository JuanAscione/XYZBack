package com.example.xyzhotelddd.http.dto;

import java.util.List;

public class ReservationResponse{
    private Long id;
    private ClientResponse client;
    private List<RoomResponseDTO> rooms;
    private String checkInDate;
    private int numberOfNights;
    private String status;

    public ReservationResponse(Long id, ClientResponse client, List<RoomResponseDTO> rooms, String checkInDate, int numberOfNights, String status) {
        this.id = id;
        this.client = client;
        this.rooms = rooms;
        this.checkInDate = checkInDate;
        this.numberOfNights = numberOfNights;
        this.status = status;
    }

    public ReservationResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientResponse getClient() {
        return client;
    }

    public void setClient(ClientResponse client) {
        this.client = client;
    }

    public List<RoomResponseDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomResponseDTO> rooms) {
        this.rooms = rooms;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ClientResponse {
        private Long id;
        private String email;

        public ClientResponse(Long id, String email) {
            this.id = id;
            this.email = email;
        }

        public ClientResponse() {

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class RoomResponseDTO {
        private String type;
        private int pricePerNight;

        public RoomResponseDTO(String type, int pricePerNight) {
            this.type = type;
            this.pricePerNight = pricePerNight;
        }

        public RoomResponseDTO() {

        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPricePerNight() {
            return pricePerNight;
        }

        public void setPricePerNight(int pricePerNight) {
            this.pricePerNight = pricePerNight;
        }
    }

}



